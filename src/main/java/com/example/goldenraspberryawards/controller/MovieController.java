package com.example.goldenraspberryawards.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.goldenraspberryawards.service.MovieService;

@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/producers")
    public Map<String, List<Map<String, Object>>> getProducersWithAwardIntervals() {
        return movieService.getProducersWithAwardIntervals();
    }
}
