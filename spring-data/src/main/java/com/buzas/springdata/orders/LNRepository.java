package com.buzas.springdata.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LNRepository extends JpaRepository<LineItem, Long>, QuerydslPredicateExecutor<LineItem> {

    @Query(value = """
        select * from ordered_items
        join ordered_items_involved_orders oiio on ordered_items.id = oiio.ordered_items_id
        where involved_orders_id = :id
""", nativeQuery = true)
    List<LineItem> findAllByOrderId(Long id);

    @Query(value = """
        delete from ordered_items_involved_orders
         where ordered_items_id = :itemId and involved_orders_id = :orderId;
""", nativeQuery = true)
    void removeByIdFromOrderById(Long itemId, Long orderId);

    @Query(value = """
        insert into ordered_items_involved_orders
        values (:itemId, :orderId);
""", nativeQuery = true)
    void addByIdToOrderId(Long itemId, Long orderId);

    void removeLineItemById(Long itemId);
}
