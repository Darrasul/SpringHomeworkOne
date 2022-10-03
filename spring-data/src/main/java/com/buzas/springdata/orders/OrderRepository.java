package com.buzas.springdata.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, QuerydslPredicateExecutor<Order> {

    @Query(value = """
            select * from springDataHomework.orders o
            join users u on u.id = o.customer_id
            where u.id = :id
""", nativeQuery = true)
    List<Order> showAllByCustomerId(Long id);

    void deleteAllById(Long id);
}
