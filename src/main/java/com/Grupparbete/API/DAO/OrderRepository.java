package com.Grupparbete.API.DAO;

import com.Grupparbete.API.Entities.Dishes;
import com.Grupparbete.API.Entities.Order;
import com.Grupparbete.API.Entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT od FROM Order o JOIN o.orderDetails od WHERE od.dish = :dish")
    List<OrderDetails> findOrderDetailsContainingDish(@Param("dish") Dishes dish);

}