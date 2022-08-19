package com.buzas.products;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;


@Getter
@Setter
public class Product {
    private Long id;
    @Size(min = 3, message = "Name too short")
    private String name;

//    @NotBlank(message = "Specify the amount")
//    @Pattern(regexp = "^\\d+$", message = "Only numbers")
    @Min(value = 0, message = "price too low")
    @Max(value = 500, message = "price too big")
    @NotNull(message = "Specify the price")
    private Integer price;
    @NotBlank(message = "Specify the currency")
    private String currency;

    public Product(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Product(String name, Integer  price, String currency) {
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
