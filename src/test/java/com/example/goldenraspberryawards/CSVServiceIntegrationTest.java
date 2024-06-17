package com.example.goldenraspberryawards;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.goldenraspberryawards.model.Movie;
import com.example.goldenraspberryawards.repository.MovieRepository;
import com.example.goldenraspberryawards.service.MovieService;

@SpringBootTest
class CSVServiceIntegrationTest {

    @Autowired
    private MovieService csvService;

    @Autowired
    private MovieRepository movieRepository;

    @BeforeEach
    public void setUp() {
        // Limpar o banco de dados antes de cada teste
        movieRepository.deleteAll();
    }

    @Test
    void testLoadMoviesFromCSV() {
        // Carregar os filmes do CSV
        csvService.loadMovies();

        // Verificar se os filmes foram salvos corretamente no banco de dados
        List<Movie> savedMovies = movieRepository.findAll();
        assertEquals(206, savedMovies.size());

        Movie movie1 = savedMovies.get(0);
        assertEquals(1980, movie1.getYear());
        assertEquals("Can't Stop the Music", movie1.getTitle());
        assertEquals("Associated Film Distribution", movie1.getStudios());
        assertEquals("Allan Carr", movie1.getProducers());
        assertTrue(movie1.isWinner());

        Movie movie2 = savedMovies.get(1);
        assertEquals(1980, movie2.getYear());
        assertEquals("Cruising", movie2.getTitle());
        assertEquals("Lorimar Productions, United Artists", movie2.getStudios());
        assertEquals("Jerry Weintraub", movie2.getProducers());
        assertFalse(movie2.isWinner());

        Movie lastMovie = savedMovies.get(savedMovies.size() - 1);
        assertEquals(2019, lastMovie.getYear());
        assertEquals("Rambo: Last Blood", lastMovie.getTitle());
        assertEquals("Lionsgate", lastMovie.getStudios());
        assertEquals("Avi Lerner, Kevin King Templeton, Yariv Lerner, and Les Weldon", lastMovie.getProducers());
        assertFalse(lastMovie.isWinner());
    }
}
