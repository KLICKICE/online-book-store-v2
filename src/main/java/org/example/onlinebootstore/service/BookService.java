package org.example.onlinebootstore.service;

import org.example.onlinebootstore.model.Book;
import java.util.List;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
