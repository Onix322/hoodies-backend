package com.hoodiesbackend.repositories;

import com.hoodiesbackend.entities.productCart.ProductCart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCart, Long> {

    @Transactional
    void deleteProductCartByProductId(Long id);

    Optional<ProductCart> getProductCartByProductId(Long id);

    @Query("FROM ProductCart pc where pc.product.id = :productId AND pc.cart.id= :cartId")
    Optional<ProductCart> getProductCartByCartId(Long productId, Long cartId);
}
