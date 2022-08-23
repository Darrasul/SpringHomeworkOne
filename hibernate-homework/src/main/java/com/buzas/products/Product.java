package com.buzas.products;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    Знаю, что такая длинна ни к чему в названии, но т.к. по ДЗ у нас негде отработать изменение длинны строки кроме
//    этого поля, параметр был добавлен.
    @Column(nullable = false, unique = true, length = 1024)
    private String title;

    @Column(nullable = false)
    private int price;

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
