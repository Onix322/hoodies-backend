package com.hoodiesbackend.controllers;

import com.hoodiesbackend.entities.product.Product;
import com.hoodiesbackend.entities.product.helpers.ProductDto;
import com.hoodiesbackend.response.Response;
import com.hoodiesbackend.response.ResponseHandler;
import com.hoodiesbackend.services.product.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
@CrossOrigin("http://localhost:4200/")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/post")
    public ResponseEntity<Response> create(@Valid @RequestBody Product body) {
        System.out.println(body);
        return ResponseHandler.ok(productService.create(body));
    }

    @PostMapping("/post/multiple")
    public ResponseEntity<Response> create(@Valid @RequestBody List<Product> list) {

        list.forEach(productService::create);

        return ResponseHandler.ok(list);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> get(@PathVariable Long id) {
        Product resource = productService.read(id);
        return ResponseHandler.ok(resource);
    }

    @GetMapping("/get")
    public ResponseEntity<Response> getAll() {
        List<ProductDto> resource = productService.readAll();
        return ResponseHandler.ok(resource);
    }

    @GetMapping("/get/amount/{amount}/{startFrom}")
    public ResponseEntity<Response> getAmount(@PathVariable(value = "amount") int amount,@PathVariable("startFrom")  int startFrom) {
        return ResponseHandler.ok(productService.readAmount(amount, startFrom));
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
