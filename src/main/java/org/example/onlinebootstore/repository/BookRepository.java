package org.example.onlinebootstore.repository;

import org.example.onlinebootstore.model.Book;
import java.util.List;

public interface BookRepository {
    Book save(Book book);

    List<Book> findAll();
}
