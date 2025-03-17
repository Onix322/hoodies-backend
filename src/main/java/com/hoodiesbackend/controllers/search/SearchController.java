package com.hoodiesbackend.controllers.search;

import com.hoodiesbackend.entities.product.helpers.search.ProductSearch;
import com.hoodiesbackend.response.Response;
import com.hoodiesbackend.response.ResponseHandler;
import com.hoodiesbackend.services.search.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PutMapping
    public ResponseEntity<Response> search(@RequestBody ProductSearch productSearch) {
        return ResponseHandler.ok(searchService.search(productSearch));
    }
}
