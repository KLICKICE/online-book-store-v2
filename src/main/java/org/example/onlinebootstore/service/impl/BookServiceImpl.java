package org.example.onlinebootstore.service.impl;

import lombok.*;
import org.example.onlinebootstore.model.Book;
import org.example.onlinebootstore.repository.BookRepository;
import org.example.onlinebootstore.service.BookService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    @Autowired
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
