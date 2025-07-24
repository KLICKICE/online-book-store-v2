package org.example.onlinebootstore.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.onlinebootstore.model.Book;
import org.example.onlinebootstore.repository.BookRepository;
import org.example.onlinebootstore.service.BookService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
