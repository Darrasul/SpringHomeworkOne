package com.buzas.products;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void initialize() {
        this.products = new ArrayList<>();
        addProductsIntoList(this.products);
    }

    private void addProductsIntoList(List<Product> list) {
        for (int i = 0; i < 5; i++) {
            list.add(new Product((long) i, "Product #" + (i + 1), (int) (Math.random() * 500)));
        }
    }

    public Optional<Product> showById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public List<Product> showContent() {
        return Collections.unmodifiableList(products);
    }
}
