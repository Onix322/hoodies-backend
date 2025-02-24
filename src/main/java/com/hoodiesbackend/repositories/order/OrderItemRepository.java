package com.hoodiesbackend.repositories.order;

import com.hoodiesbackend.entities.order.Order;
import com.hoodiesbackend.entities.order.OrderItem.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    //    List<OrderItem> order(Order order);
    List<OrderItem> findAllByOrderId(Long orderId);

}
