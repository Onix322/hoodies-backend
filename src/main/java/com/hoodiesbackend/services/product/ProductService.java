package com.hoodiesbackend.services.product;

import com.hoodiesbackend.entities.product.Product;
import com.hoodiesbackend.entities.product.helpers.ProductDto;
import com.hoodiesbackend.entities.product.helpers.ProductMapper;
import com.hoodiesbackend.exceptions.BadRequestException;
import com.hoodiesbackend.exceptions.NotFoundException;
import com.hoodiesbackend.repositories.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product create(Product product) {
        System.out.println(product.getProductImages().size());
        product.setId(null);
        return productRepository.save(product);
    }

    public Product read(Long id) {
        if (id <= 0) {
            throw new BadRequestException("Id is invalid!");
        }
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Resource not found"));
    }

    public List<ProductDto> readAll() {

        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDto)
                .toList();
    }

    public List<ProductDto> readAmount(Integer amount, Integer startFrom) {
        if(amount < 1 || startFrom < 1) throw new BadRequestException("Amount and StartFrom should be > 0");

        return productRepository.getAmountOfProducts(amount, startFrom)
                .stream()
                .map(ProductMapper::toDto)
                .toList();
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

    public ProductDto delete(Long id) {

        if (id <= 0) {
            throw new BadRequestException("Id is invalid!");
        }
        Product product = this.read(id);
//        productRepository.deleteById(product.getId());
        product.setAvailableForPurchase(false);
        return ProductMapper.toDto(this.update(product));
    }

    public ProductDto changeAvailability(Long productId, Boolean status){
        Product product = this.read(productId);
        product.setAvailableForPurchase(status);

        return ProductMapper.toDto(this.update(product));
    }
}
