package com.buzas.customers;

import com.buzas.dao.MainDao;
import com.buzas.orders.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class CustomerDao extends MainDao {
    private EntityManagerFactory factory;
    private EntityManager manager;

    public CustomerDao() {
        super();
    }

    public CustomerDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public CustomerDao(EntityManagerFactory factory, EntityManager manager) {
        this.factory = factory;
        this.manager = manager;
    }

    public List<Customer> findAll() {
        return executeWithManager(entityManager ->
                entityManager.createNamedQuery("findAllCustomers", Customer.class)).getResultList();
    }

    public Optional<Customer> findById(long id) {
        return executeWithManager(entityManager ->
                Optional.ofNullable(entityManager.find(Customer.class, id)));
    }

    public void saveOrUpdate(Customer customer) {
        executeTransaction(entityManager -> {
            if (customer.getId() == null){
                entityManager.persist(customer);
            } else {
                entityManager.merge(customer);
            }
        });
    }

    public void deleteById(long id){
        executeTransaction(entityManager ->
                entityManager.createNamedQuery("deleteCustomer")
                        .setParameter("id", id).executeUpdate());
    }
}
