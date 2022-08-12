package com.buzas.carts;

import com.buzas.products.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private List<Product> cart;

    public Cart() {
        this.cart = new ArrayList<>();
    }

    public void add(Product product) {
        cart.add(product);
    }

    public void remove(Long id) {
        cart.removeIf(p -> p.getId().equals(id));
    }

    public void removeAll() {
        cart.clear();
    }

    public int getSize() {
        return this.cart.size();
    }

    public List<Product> showContent() {
        return Collections.unmodifiableList(cart);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cart=" + cart +
                '}';
    }
}
