package com.hoodiesbackend.services.cart;

import com.hoodiesbackend.entities.cart.Cart;
import com.hoodiesbackend.entities.cart.helpers.CartDto;
import com.hoodiesbackend.entities.cart.helpers.CartMapper;
import com.hoodiesbackend.entities.productCart.ProductCart;
import com.hoodiesbackend.entities.product.Product;
import com.hoodiesbackend.exceptions.BadRequestException;
import com.hoodiesbackend.exceptions.NotFoundException;
import com.hoodiesbackend.repositories.CartRepository;
import com.hoodiesbackend.services.product.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;
    private final ProductCartService productCartService;

    public CartService(CartRepository cartRepository, ProductService productService, ProductCartService productCartService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.productCartService = productCartService;
    }

    public Cart create(Cart cart) {

        return cartRepository.save(cart);
    }

    public CartDto update(Cart cart) {

        return CartMapper.toDto(cartRepository.save(cart));
    }

    public CartDto addProductToCart(Long productId, Long userId) {

        Cart cart = cartRepository.readCartByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Cart not found!"));

        Optional<ProductCart> productCartOptional = productCartService.getByCartId(productId, cart.getId());


        if (productCartOptional.isPresent()) {
            this.increaseQuantity(productCartOptional.get());
            return this.update(cart);
        }


        ProductCart productCart = createProductCart(productId, cart);
        cart.addProduct(productCart);
        return this.update(cart);
    }

    private ProductCart createProductCart(Long productId, Cart cart) {

        Product product = productService.read(productId);
        ProductCart productCart = new ProductCart(product, cart);
        productCart.setQuantity(1);

        return productCart;
    }

    private void increaseQuantity(ProductCart productCart) {
        productCart.setQuantity(productCart.getQuantity() + 1);
        productCartService.update(productCart);
    }

    public void decreaseQuantity(ProductCart productCart) {
        productCart.setQuantity(productCart.getQuantity() - 1);
        productCartService.update(productCart);
    }

    /// ///////
    public CartDto removeProductFromCart(Long productId, Long userId) {
        Cart cart = cartRepository.readCartByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Cart not found!"));

        ProductCart product = productCartService.getByCartId(productId, cart.getId())
                .orElseThrow(() -> new NotFoundException("Product not found in cart"));

        if (product.getQuantity() > 1) {
            this.decreaseQuantity(product);
            return this.update(cart);
        }

        cart.removeProduct(product);

        CartDto cartDto = this.update(cart);
        productCartService.removeProductCartById(product.getId());

        return cartDto;
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

        Cart cart = cartRepository.readCartByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Cart not found!"));

        cart.setProducts(new ArrayList<>());

        this.update(cart);

        return cart.getProducts().isEmpty();
    }
}
