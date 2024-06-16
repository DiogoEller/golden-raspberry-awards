package com.example.goldenraspberryawards.controller;

import com.example.goldenraspberryawards.model.Movie;
import com.example.goldenraspberryawards.repository.MovieRepository;
import com.example.goldenraspberryawards.service.MovieService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/winners")
    public List<Movie> getWinningMovies() {
        return movieService.getWinningMovies();
    }

    @GetMapping("/producers")
    public Map<String, List<Map<String, Object>>> getProducersWithAwardIntervals() {
        return movieService.getProducersWithAwardIntervals();
    }
}
