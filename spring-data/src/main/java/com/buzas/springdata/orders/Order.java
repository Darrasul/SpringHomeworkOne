package com.buzas.springdata.orders;

import com.buzas.springdata.users.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "involvedOrders",
    cascade = CascadeType.MERGE)
    @Column(nullable = false)
    private Set<LineItem> orderedItems;

    @ManyToOne()
    private User customer;

    @Column
    private BigDecimal totalPrice;

    public Order(Set<LineItem> orderedItems, User customer) {
        this.orderedItems = orderedItems;
        this.customer = customer;
        this.totalPrice = BigDecimal.valueOf(0);
        for (LineItem orderedItem : orderedItems) {
            this.totalPrice = totalPrice.add(orderedItem.getCost());
        }
    }

    public Order() {
    }

    public void addToOrder(LineItem item) {
        orderedItems.add(item);
        totalPrice = BigDecimal.valueOf(totalPrice.doubleValue() + item.getCost().doubleValue());
    }

    public void removeFromOrder(LineItem item) {
        orderedItems.remove(item);
        totalPrice = BigDecimal.valueOf(totalPrice.doubleValue() - item.getCost().doubleValue());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderedItems=" + orderedItems +
                ", customer=" + customer.getUsername() +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
