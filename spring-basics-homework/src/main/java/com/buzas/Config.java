package com.buzas;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.buzas.carts")
@ComponentScan("com.buzas.products")
public class Config {
}
