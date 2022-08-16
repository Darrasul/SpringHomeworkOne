package com.buzas.products;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {
    private Map<Long, Product> productMap;
    private AtomicLong id;

    @PostConstruct
    public void init() {
        this.productMap = new ConcurrentHashMap<>();
        this.id = new AtomicLong(0);

        insert(new Product("Product #1"));
        insert(new Product("Product #2"));
        insert(new Product("Product #3"));
        insert(new Product("Product #4"));
        insert(new Product("Product #5"));
    }

    public List<Product> showAll() {
        return new ArrayList<>(productMap.values());
    }

    public Product findById(long id) {
        return productMap.get(id);
    }

    public void insert(Product product) {
        long currentId = id.incrementAndGet();
        product.setId(currentId);
        productMap.put(currentId, product);
    }

    public void insert(Product product, long requestedId) {
        productMap.putIfAbsent(requestedId, product);
    }

    public void update(Product product) {
        productMap.put(product.getId(), product);
    }

    public void delete(long id) {
        productMap.remove(id);
    }

    public void deleteAll() {
        productMap.clear();
    }
}
