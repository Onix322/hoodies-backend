package com.hoodiesbackend.services.cart;

import com.hoodiesbackend.entities.cart.Cart;
import com.hoodiesbackend.entities.cart.CartItem.CartItem;
import com.hoodiesbackend.entities.cart.helpers.CartDto;
import com.hoodiesbackend.entities.cart.helpers.CartMapper;
import com.hoodiesbackend.entities.product.Product;
import com.hoodiesbackend.exceptions.NotFoundException;
import com.hoodiesbackend.repositories.cart.CartRepository;
import com.hoodiesbackend.services.cart.cartItem.CartItemService;
import com.hoodiesbackend.services.product.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;
    private final CartItemService cartItemService;

    public CartService(CartRepository cartRepository, ProductService productService, CartItemService cartItemService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.cartItemService = cartItemService;
    }

    public Cart create(Cart body){
        body.setId(null);
        return cartRepository.save(body);
    }

    public Cart update(Cart cart){
        return cartRepository.save(cart);
    }

    public Boolean addProduct(CartItem body){
        Cart cart = this.getCartById(body.getCart().getId());
        Product product = productService.read(body.getProduct().getId());

        cart.setTotalPrice(cart.getTotalPrice() + (product.getPrice() * body.getQuantity()));

        this.update(cart);
        CartItem cartItem = cartItemService.create(body);
        System.out.println(cartItem);

        return true;
    }

    public CartDto getCartByUserId(Long userId){
        Cart cart = cartRepository.findCartByUserId(userId);
        List<CartItem> items = cartItemService.findAllByCartId(cart.getId());
        cart.setProducts(items);
        return CartMapper.toDto(cart);
    }

    public Cart getCartById(Long cartId){
        return cartRepository.findById(cartId).orElseThrow(() -> new NotFoundException("Cart not found"));
    }

    public Boolean deleteItem(Long cartId, Long itemId){
        return cartItemService.delete(cartId, itemId);
    }
}
