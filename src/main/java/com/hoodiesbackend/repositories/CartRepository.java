package com.hoodiesbackend.repositories;

import com.hoodiesbackend.entities.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> readCartByUserId(Long id);
    Boolean deleteCartByUserId(Long id);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
            "FROM Cart c JOIN c.products p " +
            "WHERE c.user.id = :userId AND p.id = :productId")
    boolean existsProductInCart(@Param("userId") Long userId, @Param("productId") Long productId);
}
