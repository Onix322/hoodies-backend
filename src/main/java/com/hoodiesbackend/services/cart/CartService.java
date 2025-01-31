package com.hoodiesbackend.services.cart;

import com.hoodiesbackend.entities.cart.Cart;
import com.hoodiesbackend.entities.dtos.CartDto;
import com.hoodiesbackend.entities.dtos.CartMapper;
import com.hoodiesbackend.entities.product.Product;
import com.hoodiesbackend.exceptions.BadRequestException;
import com.hoodiesbackend.exceptions.NotFoundException;
import com.hoodiesbackend.repositories.CartRepository;
import com.hoodiesbackend.services.product.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;

    public CartService(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    public Cart create(Cart cart) {

        return cartRepository.save(cart);
    }

    public CartDto update(Cart cart) {
        System.out.println(cart);
        return CartMapper.toDto(cartRepository.save(cart));
    }

    public CartDto addProductToCart(Long productId, Long userId) {

        Cart cart = cartRepository.readCartByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Cart not found!"));

        Product product = productService.read(productId);

        cart.addProduct(product);

        return this.update(cart);
    }

    public CartDto removeProductFromCart(Long productId, Long userId) {
        Cart cart = cartRepository.readCartByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Cart not found!"));

        Product product = productService.read(productId);

        cart.removeProduct(product);

        return this.update(cart);
    }


    public List<CartDto> getAll() {
        return cartRepository.findAll()
                .stream()
                .map(CartMapper::toDto)
                .toList();
    }

    public CartDto readByUserId(Long id) {

        Cart cart = cartRepository.readCartByUserId(id)
                .orElseThrow(() -> new NotFoundException("Cart not found!"));

        return CartMapper.toDto(cart);
    }

    public Boolean delete(Long userId) {
        if (userId <= 0) {
            throw new BadRequestException("Id is invalid!");
        }
        cartRepository.deleteCartByUserId(userId);

        return true;
    }

    public Boolean removeAll(Long userId) {

        Cart cart = cartRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Cart not found!"));

        cart.setProducts(new ArrayList<>());

        this.update(cart);

        return cart.getProducts().isEmpty();
    }

    public Boolean verifyExistenceOfProduct(Long userId, Long productId) {
        return cartRepository.existsProductInCart(userId, productId);
    }
}
