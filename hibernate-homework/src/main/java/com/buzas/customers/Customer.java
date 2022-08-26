package com.buzas.customers;

import com.buzas.orders.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customers")
@NamedQueries({
        @NamedQuery(name = "findAllCustomers", query = "select c from Customer c"),
        @NamedQuery(name = "deleteCustomer", query = "delete from Customer c where c.id = :id")
}
)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "customer_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(
            mappedBy = "customer",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE},
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<Order> orders;

    public Customer() {
    }

    public Customer(String name) {
        this.name = name;
    }

    public Customer(String name, List<Order> orders) {
        this.name = name;
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", orders=" + orders +
                '}';
    }
}
