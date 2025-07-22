package org.example.dao;

import org.example.model.Movie;

public interface MovieDao {
    Movie save(Movie movie);
    Movie get(Long id);
}
