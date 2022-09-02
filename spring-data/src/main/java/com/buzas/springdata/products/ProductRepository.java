package com.buzas.springdata.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByPriceLessThanEqual(Double maximumFilter);
    List<Product> findAllByPriceGreaterThanEqual(Double minimumFilter);
    List<Product> findAllByPriceLessThanEqualAndPriceGreaterThanEqual(Double maximumFilter, Double minimumFilter);

    @Query(value = """
        select * from product p
        where (:maximumFilter is null or p.price <= :maximumFilter)
        and (:minimumFilter is null or p.price >= :minimumFilter)
""", nativeQuery = true
    )
    List<Product> findAllByFilters(Double maximumFilter, Double minimumFilter);
}
