package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.example.Util.EntityManagerUtil;
import org.example.model.Movie;

public class JPAProductDaoImpl implements MovieDao{
    @Override
    public Movie save(Movie movie) {
        EntityManagerFactory entityManagerFactory = EntityManagerUtil.getEntityManagerFactory();
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        transaction.begin();
        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            entityManager.persist(movie);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save movie to DB", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return movie;
    }

    @Override
    public Movie get(Long id) {
        return null;
    }
}
