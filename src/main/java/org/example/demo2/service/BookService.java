package org.example.demo2.service;
import org.example.demo2.model.Book;

import java.util.List;

public interface BookService {
    Book save(Book book);
    List<Book> findAll();
}
