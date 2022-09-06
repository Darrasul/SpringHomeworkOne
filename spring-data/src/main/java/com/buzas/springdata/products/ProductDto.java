package com.buzas.springdata.products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ProductDto {

    private Long id;

    @Size(min = 3, message = "Name too short")
    private String name;

    @DecimalMin(value = "0.01", message = "price too low")
    @DecimalMin(value = "500.00", message = "price too big")
    @NotNull(message = "Specify the price")
    private BigDecimal price;

    @NotBlank(message = "Specify the currency")
    private String currency;

    public ProductDto(String name, BigDecimal price, String currency) {
        this.name = name;
        this.price = price;
        this.currency = currency;
    }

    public ProductDto() {
    }
}
