package org.example.onlinebootstore.repository;

import java.util.List;

import org.example.onlinebootstore.model.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}
