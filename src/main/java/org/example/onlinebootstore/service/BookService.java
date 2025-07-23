package org.example.onlinebootstore.service;

import java.util.List;
import org.example.onlinebootstore.model.Book;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
