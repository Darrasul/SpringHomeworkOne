package com.buzas.products;

import com.buzas.dao.MainDao;
import com.buzas.orders.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.Getter;
import lombok.Setter;

import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class ProductDao extends MainDao {

    private EntityManagerFactory factory;
    private EntityManager manager;

    public ProductDao() {
        super();
    }

    public ProductDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public ProductDao(EntityManagerFactory factory, EntityManager manager) {
        this.factory = factory;
        this.manager = manager;
    }

    public List<Product> findAll() {
       return executeWithManager(entityManager ->
               entityManager.createNamedQuery("findAllProducts", Product.class).getResultList());
    }

    public Optional<Product> findById(long id) {
        return executeWithManager(entityManager ->
                Optional.ofNullable(entityManager.find(Product.class, id)));
    }

    public void saveOrUpdate(Product product) {
        executeTransaction(entityManager -> {
           if (product.getId() == null){
               entityManager.persist(product);
           } else {
               entityManager.merge(product);
           }
        });
    }

    public void deleteById(long id){
        executeTransaction(entityManager ->
                entityManager.createNamedQuery("deleteProduct")
                        .setParameter("id", id).executeUpdate());
    }

    public List<Long> showAllOrdersIdWithProduct(long id){
        Product product = findById(id).orElseThrow(() -> new FindException("There is no such product with that ID"));
        List<Long> ids = new ArrayList<>();
        for (Order order : product.getOrders()) {
            ids.add(order.getId());
        }
        return ids;
    }
}
