package org.example.demo2.repository;

import org.example.demo2.model.Book;
import java.util.List;

public interface BookRepository {
    Book save(Book book);
    List<Book> findAll();
}
