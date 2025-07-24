package org.example.onlinebootstore.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;
import org.example.onlinebootstore.model.Book;
import org.example.onlinebootstore.repository.BookRepository;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl implements BookRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    @Transactional
    public Book save(Book book) {
        getSession().persist(book);
        return book;
    }

    @Override
    public List<Book> findAll() {
        return getSession()
                .createQuery("FROM Book", Book.class)
                .getResultList();
    }
}
