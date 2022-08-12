package com.buzas;

import com.buzas.carts.CartService;
import com.buzas.products.Product;
import com.buzas.products.ProductRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ManipulationConsole {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        CartService cartService = context.getBean(CartService.class);
        ProductRepository productRepository = context.getBean(ProductRepository.class);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("------------------------------");
            System.out.println("Commands:\n" +
                    "List - shows all available products\n" +
                    "Add - allows you to enter the product id to add it to the cart\n" +
                    "Remove - allows you to enter the product id to remove it from the cart\n" +
                    "Cart - shows the products in the cart\n" +
                    "Refresh - clears the cart\n" +
                    "Exit - close console");
            System.out.println("Enter the command");
            String command = scanner.nextLine().toLowerCase().trim();
            if (command.equals("exit")){
                break;
            }
            switch (command) {
                case "list":
                    List<Product> products = productRepository.showContent();
                    for (Product product : products) {
                        System.out.println(product);
                    }
                    break;
                case "add":
                    System.out.println("Enter product id to add it to the cart");
                    int requestToAdd = scanner.nextInt();
                    Optional<Product> product = productRepository.showById((long) requestToAdd);
                    if (product.isEmpty()){
                        System.err.println("This ID does not exist");
                    } else {
                        cartService.addProduct(product.get());
                    }
                    break;
                case "remove":
                    System.out.println("Enter product id to remove it from the cart");
                    int request = scanner.nextInt();
                    if (productRepository.showById((long) request).isEmpty()){
                        System.err.println("This ID does not exist at first place");
                    } else {
                        cartService.removeProduct(productRepository.showById((long) request).get());
                    }
                    break;
                case "cart":
                    for (Product productInCart : cartService.getCart().showContent()) {
                        System.out.println(productInCart);
                    }
                    break;
                case "refresh":
                    cartService.removeAll();
                    break;
            }
        }

        scanner.close();
        context.close();
    }
}
