package com.buzas.products;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.cfg.Configuration;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class ProductDao implements Closeable {
    private static final EntityManagerFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

    private static final EntityManager manager = factory.createEntityManager();

    public Product findById(Long id) {
        return manager.find(Product.class, id);
    }

    public List<Product> findAll() {
        List<Product> products = manager.createQuery("select p from Product p", Product.class).getResultList();
        return Collections.unmodifiableList(products);
    }

    public void deleteById(Long id) {
        manager.getTransaction().begin();
        try {
            manager.createQuery("delete from Product p where p.id=" + id).executeUpdate();
        } catch (IllegalArgumentException e) {
            System.err.println("There are problems with a deleteById void");
            e.printStackTrace();
            manager.getTransaction().rollback();
        }
        manager.getTransaction().commit();
    }

    public void saveOrUpdate(Product product) {
        manager.getTransaction().begin();
        try {
            manager.persist(product);
        } catch (EntityExistsException e) {
            manager.merge(product);
        } catch (IllegalArgumentException e) {
            System.err.println("There are problems with a saveOrUpdate void");
            e.printStackTrace();
            manager.getTransaction().rollback();
        }
        manager.getTransaction().commit();
    }

    @Override
    public void close() throws IOException {
        manager.close();
    }
}
