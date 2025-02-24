package com.hoodiesbackend.repositories.cart;

import com.hoodiesbackend.entities.cart.CartItem.CartItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findAllByCartId(Long cartId);

    @Transactional
    Integer deleteCartItemByCartIdAndId(Long cartId, Long id);

    Optional<CartItem> findCartItemByCartIdAndId(Long cartId, Long id);

    Optional<CartItem> findCartItemByCartIdAndProductId(Long cartId, Long productId);

    Boolean existsCartItemByCartIdAndProductId(Long cartId, Long productId);
}
