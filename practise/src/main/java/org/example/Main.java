package org.example;

import org.example.dao.MovieDao;
import org.example.dao.MovieDaoImpl;
import org.example.model.Movie;

public class Main {
    public static void main(String[] args) {
        MovieDao movieDao = new MovieDaoImpl();

        Movie Deadpool = new Movie();
        Deadpool.setTitle("Deadpool");
        Deadpool.setDescription("A mercenary is sent to the moon " +
                "to help a young woman who has been lost.");
        Movie savedPool = movieDao.save(Deadpool);
        System.out.println(savedPool);
    }
}