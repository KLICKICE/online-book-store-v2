package org.example.onlinebootstore.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Сутність, що представляє книгу в онлайн-магазині.
 */
@Setter
@Getter
@Entity
@Table(name = "books")
public class Book {
    /** Унікальний ідентифікатор книги (первинний ключ). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Опис книги. */
    private String description;

    /** Посилання на зображення обкладинки книги. */
    private String coverImage;

    /** Назва книги. Не може бути null. */
    @Column(nullable = false)
    private String title;

    /** Автор книги. Не може бути null. */
    @Column(nullable = false)
    private String author;

    /** ISBN книги. Має бути унікальним та не null. */
    @Column(nullable = false, unique = true)
    private String isbn;

    /** Ціна книги. Не може бути null. */
    @Column(nullable = false)
    private BigDecimal price;
}
