package com.example.goldenraspberryawards.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.goldenraspberryawards.model.Movie;
import com.example.goldenraspberryawards.repository.MovieRepository;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Value("${csv.file-path}")
    private String csvFilePath;

    // Método para carregar os filmes ao iniciar a aplicação
    @PostConstruct
    public void loadMovies() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(csvFilePath)))) {
            // Lê o arquivo CSV e mapeia para objetos Movie
            List<Movie> movies = br.lines().skip(1).map(line -> {
                String[] details = line.split(";");
                Movie movie = new Movie();
                movie.setYear(Integer.parseInt(details[0]));
                movie.setTitle(details[1]);
                movie.setStudios(details[2]);
                movie.setProducers(details[3]);
                movie.setWinner(details.length > 4 && "yes".equals(details[4]));
                return movie;
            }).collect(Collectors.toList());
            // Salva todos os filmes no banco de dados
            movieRepository.saveAll(movies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para obter todos os filmes
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
    
    // Método para obter filmes vencedores
    public List<Movie> getWinningMovies() {
        return movieRepository.findByWinner(true);
    }

    // Método para calcular intervalos entre prêmios para produtores
    public Map<String, List<Map<String, Object>>> getProducersWithAwardIntervals() {
        List<Movie> winningMovies = getWinningMovies();
        Map<String, List<Integer>> producerWins = new HashMap<>();

        // Organiza os anos de vitória por produtor
        for (Movie movie : winningMovies) {
            String[] producers = movie.getProducers().split(",| and ");
            for (String producer : producers) {
                producer = producer.trim();
                // Verificação de segurança para evitar adicionar produtores com valores em branco
                if (!producer.equals("")) {
                    // Adiciona o produtor e seus anos de vitória a um mapa
                    if (!producerWins.containsKey(producer)) {
                        producerWins.put(producer, new ArrayList<>());
                    }
                    producerWins.get(producer).add(movie.getYear());
                }
            }
        }

        List<Map<String, Object>> minIntervals = new ArrayList<>();
        List<Map<String, Object>> maxIntervals = new ArrayList<>();

        // Calcula os intervalos entre prêmios para cada produtor
        for (Map.Entry<String, List<Integer>> entry : producerWins.entrySet()) {
            String producer = entry.getKey();
            List<Integer> years = entry.getValue();
            if (years.size() > 1) {
                // Ordena os anos de vitória
                Collections.sort(years);
                for (int i = 1; i < years.size(); i++) {
                    int interval = years.get(i) - years.get(i - 1);
                    // Cria um mapa para armazenar detalhes do intervalo
                    Map<String, Object> intervalMap = new LinkedHashMap<>();
                    intervalMap.put("producer", producer);
                    intervalMap.put("interval", interval);
                    intervalMap.put("previousWin", years.get(i - 1));
                    intervalMap.put("followingWin", years.get(i));

                    // Atualiza a lista de intervalos mínimos
                    if (minIntervals.isEmpty() || interval < (int) minIntervals.get(0).get("interval")) {
                        minIntervals.clear();
                        minIntervals.add(intervalMap);
                    } else if (interval == (int) minIntervals.get(0).get("interval")) {
                        minIntervals.add(intervalMap);
                    }

                    if (maxIntervals.isEmpty() || interval > (int) maxIntervals.get(0).get("interval")) {
                        maxIntervals.clear();
                        maxIntervals.add(intervalMap);
                    } else if (interval == (int) maxIntervals.get(0).get("interval")) {
                        maxIntervals.add(intervalMap);
                    }
                }
            }
        }

        // Ordenar as listas por "previousWin" em ordem crescente
        minIntervals.sort(Comparator.comparingInt(o -> (int) o.get("previousWin")));
        maxIntervals.sort(Comparator.comparingInt(o -> (int) o.get("previousWin")));

        // Cria o resultado final mantendo a ordem de inserção
        Map<String, List<Map<String, Object>>> result = new LinkedHashMap<>();
        result.put("min", minIntervals);
        result.put("max", maxIntervals);

        return result;
    }
}
