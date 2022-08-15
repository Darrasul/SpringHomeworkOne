package com.buzas.carts;

import com.buzas.products.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope("prototype")
public class CartService {
    private Cart cart;

    @PostConstruct
    public void initialize() {
        this.cart = new Cart();
    }

    public void addProduct(Product product) {
        cart.add(product);
    }

    public void removeProduct(Product product) {
        boolean isHere = false;
        for (Product productInCart : cart.showContent()) {
            if (productInCart.getId().equals(product.getId())) {
                isHere = true;
                cart.remove(product.getId());
            }
        }
        System.out.println("There is no such product in the cart");
    }

    public void removeAll() {
        cart.removeAll();
    }

    public int getCartLength() {
        return cart.getSize();
    }

    public Cart getCart() {
        return cart;
    }
}
