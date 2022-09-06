package com.buzas.springdata.products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, message = "Name too short")
    @Column(length = 1024)
    private String name;

    @DecimalMin(value = "0.01", message = "price too low")
    @DecimalMin(value = "500.00", message = "price too big")
    @NotNull(message = "Specify the price")
    @Column(nullable = false)
    private Double price;

    @NotBlank(message = "Specify the currency")
    @Column(nullable = false, length = 1024)
    private String currency;

    public Product(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Product(String name, Double  price, String currency) {
        this.name = name;
        this.price = price;
        this.currency = currency;
    }

    public Product(String name) {
        this.name = name;
    }

    public Product() {
    }
}
