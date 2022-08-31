package com.buzas.springdata.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByPriceLessThanEqual(Double maximumFilter);
    List<Product> findAllByPriceGreaterThanEqual(Double minimumFilter);
    List<Product> findAllByPriceLessThanEqualAndPriceGreaterThanEqual(Double maximumFilter, Double minimumFilter);
}
