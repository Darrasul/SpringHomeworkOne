package com.buzas.orders;

import com.buzas.customers.Customer;
import com.buzas.products.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name = "findAllOrders", query = "select o from Order o"),
        @NamedQuery(name = "findAllOrdersOfUser", query = "select o from Order o join Customer c where c.id = :id"),
        @NamedQuery(name = "deleteOrder", query = "delete from Order o where o.id = :id"),
        @NamedQuery(name = "getAllCustomerOrders", query = "select o.products from Order o where customer.id = :id")
})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToMany
    private List<Product> products;

    @Column
    private int totalCost;

    public Order() {
    }

    public Order(Customer customer, List<Product> products) {
        this.customer = customer;
        this.products = products;
        this.totalCost = 0;
        for (Product product : products) {
            this.totalCost += product.getPrice();
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer_name=" + customer.getName() +
                ", customer_id=" + customer.getId() +
                ", products=" + products +
                ", totalCost=" + totalCost +
                '}';
    }
}
