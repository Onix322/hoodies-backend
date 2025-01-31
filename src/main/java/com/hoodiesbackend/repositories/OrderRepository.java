package com.hoodiesbackend.repositories;

import com.hoodiesbackend.entities.order.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Modifying
    @Transactional
    @Query("Delete Order o WHERE o.user.id=:userId AND o.id=:orderId")
    void deleteOrderByUserId(@Param("userId") Long userId, @Param("orderId") Long orderId);

    void deleteAllByUserId(Long userId);
}
