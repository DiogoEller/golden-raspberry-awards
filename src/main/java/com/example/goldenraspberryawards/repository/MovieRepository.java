package com.example.goldenraspberryawards.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.goldenraspberryawards.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByWinner(Boolean winner);
}
