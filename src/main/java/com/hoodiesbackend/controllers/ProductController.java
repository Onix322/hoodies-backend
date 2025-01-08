package com.hoodiesbackend.controllers;

import com.hoodiesbackend.entities.product.Product;
import com.hoodiesbackend.entities.response.Response;
import com.hoodiesbackend.entities.response.ResponseHandler;
import com.hoodiesbackend.services.impl.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController implements CrudController<Product>{

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Response<Product> create(@RequestBody Product body) {
        return ResponseHandler.ok(productService.create(body));
    }

    @GetMapping("/{id}")
    public Response<Optional<Product>> get(@PathVariable Long id) {

        Optional<Product> resource = productService.read(id);

        return resource.isPresent() ?
                ResponseHandler.ok(resource) :
                ResponseHandler.notFound();
    }

    @GetMapping
    public Response<List<Product>> getAll() {

        List<Product> resource = productService.readAll();

        return !resource.isEmpty() ?
                ResponseHandler.ok(resource) :
                ResponseHandler.notFound();
    }

    @PutMapping
    public Response<Product> update(@RequestBody Product body) {
        return ResponseHandler.ok(productService.create(body));
    }

    @DeleteMapping("/{id}")
    public Response<Product> delete(@PathVariable Long id) {

        Response<Optional<Product>> resource =  this.get(id);

        System.out.println(resource);
        if(resource.isResponsePresent()){
            productService.delete(resource.getResponse().get().getId());
            return ResponseHandler.ok(resource.getResponse().get());
        }

        return ResponseHandler.notFound();
    }
}
