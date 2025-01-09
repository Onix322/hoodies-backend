package com.hoodiesbackend.controllers;

import com.hoodiesbackend.entities.product.Product;
import com.hoodiesbackend.entities.response.Response;
import com.hoodiesbackend.entities.response.ResponseHandler;
import com.hoodiesbackend.services.impl.ProductService;
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

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody Product body) {
        return ResponseHandler.ok(productService.create(body));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable Long id) {

        Product resource = productService.read(id);

        return ResponseHandler.ok(resource);
    }

    @GetMapping
    public ResponseEntity<Response> getAll() {

        List<Product> resource = productService.readAll();

        return ResponseHandler.ok(resource);
    }

    /////////////////////////////////////////////////////
    /////////////////////////////////////////////////////
/////////////////////////////////////////////////////
    @PutMapping
    public ResponseEntity<Response> update(@RequestBody Product body) {
        return ResponseHandler.ok(productService.create(body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {

        return ResponseHandler.ok(productService.delete(id));
    }
}
