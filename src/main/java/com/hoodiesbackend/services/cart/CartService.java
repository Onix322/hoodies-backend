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

    public Cart create(Cart body) {
        body.setId(null);
        return cartRepository.save(body);
    }

    public Cart update(Cart cart) {
        return cartRepository.save(cart);
    }

    public Boolean addProduct(CartItem cartItemBody) {
        Cart cart = this.getCartById(cartItemBody.getCart().getId());
        Product product = productService.read(cartItemBody.getProduct().getId());

        if (cartItemService.contains(cart.getId(), cartItemBody.getProduct().getId())) {

            System.out.println("Id pentru bpdy " + cartItemBody.getProduct().getId() + " " + cart.getId());

            CartItem cartItem = cartItemService.findItem(cart.getId(), cartItemBody.getProduct().getId());

            cartItem.setQuantity(cartItem.getQuantity() + 1);

            cartItemService.update(cartItem);
        } else {
            cartItemService.create(cartItemBody);
        }

        cart.setTotalPrice(cart.getTotalPrice() + (product.getPrice() * cartItemBody.getQuantity()));
        this.update(cart);

        return true;
    }

    public CartDto getCartByUserId(Long userId) {
        Cart cart = cartRepository.findCartByUserId(userId);
        List<CartItem> items = cartItemService.findAllByCartId(cart.getId());
        cart.setProducts(items);
        return CartMapper.toDto(cart);
    }

    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow(() -> new NotFoundException("Cart not found"));
    }

    public Boolean deleteItem(Long cartId, Long itemId) {

        CartItem cartItem = cartItemService.findItemForCart(cartId, itemId);
        Cart cart = this.getCartById(cartId);

        if (cartItem.getQuantity() > 1) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            cart.setTotalPrice(cart.getTotalPrice() - cartItem.getProduct().getPrice());
            this.update(cart);
            return true;
        } else {
            return cartItemService.delete(cartId, itemId);
        }
    }
}
