package com.hoodiesbackend.services.cart.cartItem;

import com.hoodiesbackend.entities.cart.CartItem.CartItem;
import com.hoodiesbackend.exceptions.NotFoundException;
import com.hoodiesbackend.repositories.cart.CartItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;

    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public CartItem create(CartItem body){
        return cartItemRepository.save(body);
    }

    public List<CartItem> findAllByCartId(Long cartId){
        return cartItemRepository.findAllByCartId(cartId);
    }

    public CartItem findItemFroCart(Long cartId, Long itemId){
        return cartItemRepository.findCartItemByCartIdAndId(cartId, itemId)
                .orElseThrow(() -> new NotFoundException("Item not found in cart!"));
    }

    public boolean delete(Long cartId, Long itemId){

        Integer integerStatus = cartItemRepository.deleteCartItemByCartIdAndId(cartId, itemId);
        return integerStatus > 0;
    }
}
