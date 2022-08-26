package com.buzas.products;

import com.buzas.orders.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "findAllProducts", query = "select p from Product p"),
        @NamedQuery(name = "deleteProduct", query = "delete from Product p where p.id = :id"),
        @NamedQuery(name = "countPrice", query = "select p.price from Product p where p.id = :id")
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 1024)
    private String title;

    @Column(nullable = false)
    private int price;

    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
    private List<Order> orders;

    public Product(String title, int  price) {
        this.title = title;
        this.price = price;
    }

    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
