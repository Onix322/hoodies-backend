package com.hoodiesbackend.services.cart;

import com.hoodiesbackend.entities.cart.Cart;
import com.hoodiesbackend.entities.product.Product;
import com.hoodiesbackend.exceptions.BadRequestException;
import com.hoodiesbackend.exceptions.NotFoundException;
import com.hoodiesbackend.repositories.CartRepository;
import com.hoodiesbackend.services.product.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;

    public CartService(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    public Cart create(Cart cart){
        return cartRepository.save(cart);
    }

    public Cart update(Cart cart){
        System.out.println(cart);
        return cartRepository.save(cart);
    }

    public Cart addProductToCart(Long productId, Long userId){

        Cart cart = this.readByUserId(userId);
        Product product = productService.read(productId);

        cart.addProduct(product);

        return this.update(cart);
    }
    public Cart removeProductFromCart(Long productId, Long userId){
        Cart cart = this.readByUserId(userId);
        Product product = productService.read(productId);

        cart.removeProduct(product);

        return this.update(cart);
    }


    public List<Cart> getAll(){
        return cartRepository.findAll();
    }

    public Cart readByUserId(Long id){
        return cartRepository.readCartByUserId(id)
                .orElseThrow(() -> new NotFoundException("Cart not found!"));
    }

    public Boolean delete(Long id){
        if (id <= 0) {
            throw new BadRequestException("Id is invalid!");
        }
        cartRepository.deleteById(id);

        return true;
    }
}
