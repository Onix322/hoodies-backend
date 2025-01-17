package com.hoodiesbackend.services.cart;

import com.hoodiesbackend.entities.cart.Cart;
import com.hoodiesbackend.exceptions.BadRequestException;
import com.hoodiesbackend.repositories.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart create(Cart cart){
        return cartRepository.save(cart);
    }

    public Cart update(Cart cart){
        System.out.println(cart);
        return cartRepository.save(cart);
    }

    public List<Cart> getAll(){
        return cartRepository.findAll();
    }

    public Boolean delete(Long id){
        if (id <= 0) {
            throw new BadRequestException("Id is invalid!");
        }
        cartRepository.deleteById(id);

        return true;
    }
}
