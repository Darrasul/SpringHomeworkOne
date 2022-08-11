package com.buzas.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ProductRepository {

    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();
    private final AtomicLong id = new AtomicLong(0);

    public List<Product> showAll() {
        return new ArrayList<>(productMap.values());
    }

    public void add(Product product){
        long id = this.id.incrementAndGet();
        product.setId(id);
        productMap.put(id, product);
    }

    public Product findById(long id) {
        return productMap.get(id);
    }
}
