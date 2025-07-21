package org.example.demo2;

import org.example.demo2.model.Book;
import org.example.demo2.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.math.BigDecimal;

@SpringBootApplication
public class Demo2Application {
    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BookService bookService) {
        return args -> {
            final Book book = new Book();
            book.setTitle("Lost from Light");
            book.setAuthor("Michael Connelly");
            book.setIsbn("9780451524935");
            book.setPrice(BigDecimal.valueOf(1000));
            book.setDescription("Best Novel");
            book.setCoverImage("Sunless.jpg");
            bookService.save(book);
            bookService.findAll().forEach(System.out::println);
        };
    }
}

