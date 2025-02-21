package com.hoodiesbackend.services.cart;

import com.hoodiesbackend.entities.productCart.ProductCart;
import com.hoodiesbackend.repositories.ProductCartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCartService {

    private final ProductCartRepository productCartRepository;

    public ProductCartService(ProductCartRepository productCartRepository) {
        this.productCartRepository = productCartRepository;
    }

    public ProductCart create(ProductCart product) {
        return productCartRepository.save(product);
    }

    public ProductCart update(ProductCart product) {
        return productCartRepository.save(product);
    }

    public Optional<ProductCart> getByProductId(Long id) {
        return productCartRepository.getProductCartByProductId(id);
    }

    public Optional<ProductCart> getByCartId(Long productId, Long cartId) {
        return productCartRepository.getProductCartByCartId(productId, cartId);
    }

    public List<ProductCart> getAll() {
        return productCartRepository.findAll();
    }

    public void removeProductCartById(Long id) {
        productCartRepository.deleteById(id);
    }
}
