package com.hoodiesbackend.controllers;

import com.hoodiesbackend.entities.product.Product;
import com.hoodiesbackend.entities.product.dtos.productDto.ProductDto;
import com.hoodiesbackend.entities.response.Response;
import com.hoodiesbackend.entities.response.ResponseHandler;
import com.hoodiesbackend.services.impl.product.ProductDtoService;
import com.hoodiesbackend.services.impl.product.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController implements CrudController<Product> {

    private final ProductService productService;
    private final ProductDtoService productDtoService;

    public ProductController(ProductService productService, ProductDtoService productDtoService) {
        this.productService = productService;
        this.productDtoService = productDtoService;
    }

    @PostMapping("/post")
    public ResponseEntity<Response> create(@Valid @RequestBody Product body) {
        System.out.println(body);
        return ResponseHandler.ok(productService.create(body));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> get(@PathVariable Long id) {
        Product resource = productService.read(id);
        return ResponseHandler.ok(resource);
    }

    @GetMapping("/get")
    public ResponseEntity<Response> getAll() {
        List<ProductDto> resource = productDtoService.readAll();
        return ResponseHandler.ok(resource);
    }

    @PutMapping("/put")
    public ResponseEntity<Response> update(@RequestBody Product body) {
        return ResponseHandler.ok(productService.update(body));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        return ResponseHandler.ok(productService.delete(id));
    }
}
