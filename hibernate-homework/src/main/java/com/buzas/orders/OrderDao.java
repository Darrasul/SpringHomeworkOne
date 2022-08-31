package com.buzas.orders;

import com.buzas.dao.MainDao;
import com.buzas.products.Product;
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
public class OrderDao extends MainDao {
    private EntityManagerFactory factory;
    private EntityManager manager;

    public OrderDao() {
        super();
    }

    public OrderDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public OrderDao(EntityManagerFactory factory, EntityManager manager) {
        this.factory = factory;
        this.manager = manager;
    }

    public List<Order> findAll() {
        return executeWithManager(entityManager ->
                entityManager.createNamedQuery("findAllOrders", Order.class).getResultList());
    }

    public Optional<Order> findById(long id) {
        return executeWithManager(entityManager ->
                Optional.ofNullable(entityManager.find(Order.class, id)));
    }

    public List<Order> findAllCustomerOrderById(long id) {
        return executeWithManager(entityManager ->
                entityManager.createNamedQuery("findAllOrdersOfUser", Order.class).getResultList());
    }

    public void saveOrUpdate(Order order){
        executeTransaction(entityManager -> {
            if (order.getId() == null){
                entityManager.persist(order);
            } else {
                entityManager.merge(order);
            }
        });
    }

    public void deleteByOrderId(long id) {
        executeTransaction(entityManager ->
                entityManager.createNamedQuery("deleteOrder")
                        .setParameter("id", id).executeUpdate());
    }

    public int findTotalCostByOrderId(long id){
        Order order = findById(id).orElseThrow(() -> new FindException("There is no such an order with that ID"));
        return order.getTotalCost();
    }

    public List<Order> findAllOrdersByCustomerId(long id){
        return executeWithManager(entityManager ->
                entityManager.createNamedQuery("getAllCustomerOrders", Order.class)
                        .setParameter("id", id).getResultList());
    }
}
