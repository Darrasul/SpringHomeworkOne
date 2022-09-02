package com.buzas.springdata.products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
public class ProductDto {

    private Long id;

    @Size(min = 3, message = "Name too short")
    private String name;

    @Min(value = 0, message = "price too low")
    @Max(value = 500, message = "price too big")
    @NotNull(message = "Specify the price")
    private Double price;

    @NotBlank(message = "Specify the currency")
    private String currency;

    public ProductDto(String name, Double price, String currency) {
        this.name = name;
        this.price = price;
        this.currency = currency;
    }

    public ProductDto() {
    }
}
