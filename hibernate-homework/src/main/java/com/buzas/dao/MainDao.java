package com.buzas.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;

import java.io.Closeable;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;

public class MainDao implements Closeable {

    private static EntityManagerFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();;

    private static EntityManager manager;

    public MainDao() {
    }

    protected <O> O executeWithManager(Function<EntityManager, O> function) {
        if (manager != null) {
            manager.close();
        }
        manager = factory.createEntityManager();
        try {
            return function.apply(manager);
        } finally {
            manager.close();
        }
    }

    protected void executeTransaction(Consumer<EntityManager> consumer){
        if (manager != null) {
            manager.close();
        }
        manager = factory.createEntityManager();
        try {
            manager.getTransaction().begin();
            consumer.accept(manager);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
        } finally {
            manager.close();
        }
    }

    @Override
    public void close() throws IOException {
        manager.close();
        factory.close();
    }
}
