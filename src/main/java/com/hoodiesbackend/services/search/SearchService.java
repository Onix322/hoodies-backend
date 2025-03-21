package com.hoodiesbackend.services.search;

import com.hoodiesbackend.entities.product.Product;
import com.hoodiesbackend.entities.product.helpers.ProductDto;
import com.hoodiesbackend.entities.product.helpers.ProductMapper;
import com.hoodiesbackend.entities.product.helpers.search.ProductSearch;
import com.hoodiesbackend.entities.product.helpers.search.QueryService;
import com.hoodiesbackend.entities.product.helpers.search.SearchLogicalOperators;
import com.hoodiesbackend.entities.product.helpers.search.SearchMatchingOperators;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private final EntityManager entityManager;
    private final QueryService queryService;

    public SearchService(EntityManager entityManager, QueryService queryService) {
        this.entityManager = entityManager;
        this.queryService = queryService;
    }


    public List<ProductDto> search(ProductSearch productSearch) {

        return executeQuery(productSearch);
    }

    public List<ProductDto> executeQuery(ProductSearch productSearch) {

        String query = createQuery(productSearch);

        return entityManager.createQuery(query, Product.class)
                .getResultList()
                .stream()
                .map(ProductMapper::toDto)
                .toList();
    }

    private String createQuery(ProductSearch productSearch) {
        queryService.cleanQuery();

        return queryIn(productSearch);
    }

    private String queryIn(ProductSearch productSearch) {

        this.queryService.cleanQuery();

        this.queryService.addTemplate();

        if (productSearch.getTitle() != null) {
            this.queryService.queryFromList(
                    List.copyOf(productSearch.getTitle()),
                    "title",
                    SearchLogicalOperators.AND,
                    SearchMatchingOperators.LIKE
            );
        }

        if (productSearch.getAvailableForPurchase() != null) {
            this.queryService.addOpenParentheses();
            this.queryService.addColumnName("availableForPurchase")
                    .addEqual(productSearch.getRating());
            this.queryService.addCloseParentheses();
        }

        if (productSearch.getProductColor() != null) {
            this.queryService.queryFromList(
                    List.copyOf(productSearch.getProductColor()),
                    "productColor",
                    SearchLogicalOperators.OR,
                    SearchMatchingOperators.EQUALS
            );
        }

        if (productSearch.getRating() != null) {
            this.queryService.addOpenParentheses();
            this.queryService.addColumnName("rating")
                    .addEqual(productSearch.getRating());
            this.queryService.addCloseParentheses();
        }

        if (productSearch.getSize() != null) {
            this.queryService.queryFromList(
                    List.copyOf(productSearch.getSize()),
                    "size",
                    SearchLogicalOperators.OR,
                    SearchMatchingOperators.EQUALS
            );
        }

        System.out.println(this.queryService.buildQuery());
        return this.queryService.buildQuery();
    }
}
