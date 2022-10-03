package com.buzas.springdata.orders;

import com.buzas.springdata.products.Product;
import com.buzas.springdata.products.ProductDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "ordered_items")
public class LineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private BigDecimal cost;

    @Column(nullable = false)
    private String currency;

    @ManyToMany
    private Set<LineItem> involvedOrders;

    public LineItem(Product product) {
        this.product = product;
        this.cost = product.getPrice();
        this.currency = product.getCurrency();
    }

    public LineItem(ProductDto productDto) {
        this.product = new Product();
        this.cost = product.getPrice();
        this.currency = product.getCurrency();
    }

    public LineItem() {
    }

    @Override
    public String toString() {
        return "LineItem{" +
                ", product=" + product +
                ", cost=" + cost + " " + currency +
                '}';
    }
}
