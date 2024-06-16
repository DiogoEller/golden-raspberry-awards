package com.example.goldenraspberryawards.service;

import com.example.goldenraspberryawards.model.Movie;
import com.example.goldenraspberryawards.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @PostConstruct
    public void loadMovies() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/movielist.csv")))) {
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
            movieRepository.saveAll(movies);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public List<Movie> getWinningMovies() {
        return movieRepository.findByWinner(true);
    }

    public Map<String, List<Map<String, Object>>> getProducersWithAwardIntervals() {
        List<Movie> winningMovies = getWinningMovies();
        Map<String, List<Integer>> producerWins = new HashMap<>();

        // Organizar os filmes vencedores por produtor
        for (Movie movie : winningMovies) {
            String[] producers = movie.getProducers().split(",| and ");
            for (String producer : producers) {
                producer = producer.trim();
                if (!producerWins.containsKey(producer)) {
                    producerWins.put(producer, new ArrayList<>());
                }
                producerWins.get(producer).add(movie.getYear());
            }
        }

        List<Map<String, Object>> minIntervals = new ArrayList<>();
        List<Map<String, Object>> maxIntervals = new ArrayList<>();

        // Calcular os intervalos para cada produtor
        for (Map.Entry<String, List<Integer>> entry : producerWins.entrySet()) {
            String producer = entry.getKey();
            List<Integer> years = entry.getValue();
            if (years.size() > 1) {
                Collections.sort(years);
                for (int i = 1; i < years.size(); i++) {
                    int interval = years.get(i) - years.get(i - 1);
                    Map<String, Object> intervalMap = new HashMap<>();
                    intervalMap.put("producer", producer);
                    intervalMap.put("interval", interval);
                    intervalMap.put("previousWin", years.get(i - 1));
                    intervalMap.put("followingWin", years.get(i));

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

        Map<String, List<Map<String, Object>>> result = new HashMap<>();
        result.put("min", minIntervals);
        result.put("max", maxIntervals);

        return result;
    }
}
