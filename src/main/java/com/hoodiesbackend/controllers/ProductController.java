package com.hoodiesbackend.controllers;

import com.hoodiesbackend.entities.product.Product;
import com.hoodiesbackend.entities.response.Response;
import com.hoodiesbackend.entities.response.ResponseHandler;
import com.hoodiesbackend.services.impl.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController implements CrudController<Product>{

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/post")
    public ResponseEntity<Response> create(@Valid @RequestBody Product body) {
        return ResponseHandler.ok(productService.create(body));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> get(@PathVariable Long id) {
        Product resource = productService.read(id);
        return ResponseHandler.ok(resource);
    }

    @GetMapping("/get")
    public ResponseEntity<Response> getAll() {
        List<Product> resource = productService.readAll();
        return ResponseHandler.ok(resource);
    }

    @PutMapping("/put")
    public ResponseEntity<Response> update(@RequestBody Product body) {
        return ResponseHandler.ok(productService.create(body));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {

        return ResponseHandler.ok(productService.delete(id));
    }
}
