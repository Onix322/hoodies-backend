package com.hoodiesbackend.services.cart.cartItem;

import com.hoodiesbackend.entities.cart.CartItem.CartItem;
import com.hoodiesbackend.exceptions.BadRequestException;
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
        body.setId(null);
        return cartItemRepository.save(body);
    }

    public CartItem update(CartItem body){
        return cartItemRepository.save(body);
    }

    public List<CartItem> findAllByCartId(Long cartId){
        if(cartId < 1) throw new BadRequestException("Cart Id should be > 0");
        return cartItemRepository.findAllByCartId(cartId);
    }

    public CartItem findItemForCart(Long cartId, Long itemId){
        if(cartId < 1 || itemId < 1) throw new BadRequestException("Cart Id and Item id should be > 0");

        return cartItemRepository.findCartItemByCartIdAndId(cartId, itemId)
                .orElseThrow(() -> new NotFoundException("Item not found in cart!"));
    }

    public CartItem findItem(Long cartId, Long productId){
        if(cartId < 1 || productId < 1) throw new BadRequestException("Cart Id and Product id should be > 0");

        return cartItemRepository.findCartItemByCartIdAndProductId(cartId, productId)
                .orElseThrow(() -> new NotFoundException("Item not found in cart!"));
    }

    public boolean delete(Long cartId, Long itemId){
        if(cartId < 1 || itemId < 1) throw new BadRequestException("Cart Id and Item id should be > 0");

        return cartItemRepository.deleteCartItemByCartIdAndId(cartId, itemId) > 0;
    }

    public Boolean contains(Long cartId, Long productId){
        if(cartId < 1 || productId < 1) throw new BadRequestException("Cart Id and Product id should be > 0");

        return cartItemRepository.existsCartItemByCartIdAndProductId(cartId, productId);
    }
}
