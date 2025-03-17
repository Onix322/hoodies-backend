package com.hoodiesbackend.services.search;

import com.hoodiesbackend.entities.product.Product;
import com.hoodiesbackend.entities.product.helpers.ProductDto;
import com.hoodiesbackend.entities.product.helpers.ProductMapper;
import com.hoodiesbackend.entities.product.helpers.search.ProductQuery;
import com.hoodiesbackend.entities.product.helpers.search.ProductSearch;
import com.hoodiesbackend.entities.product.helpers.search.SearchLogicalOperators;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Service
public class SearchService {
    private final EntityManager entityManager;

    public SearchService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public List<ProductDto> search(ProductSearch productSearch){

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

        Field[] fields = productSearch.getClass().getDeclaredFields();

        ProductQuery productQuery = new ProductQuery();


        try {
            for (int j = 0; j < fields.length; j++) {
                Field field = fields[j];
                field.setAccessible(true);
                Object object = field.get(productSearch);

                if (object == null) {
                    continue;
                }

                productQuery.addOpenParentheses();
                for (int i = 0; i < ((List<?>) object).size(); i++) {

                    productQuery.addColumnName(field.getName());

                    if(field.getName().equals("title")){
                        productQuery.addLike(((List<?>) object).get(i).toString());
                    } else {
                        productQuery.addEqual(((List<?>) object).get(i).toString());
                    }

                    if(j == fields.length - 1){
                        productQuery.addCloseParentheses();
                        break;
                    }

                    if(i == ((List<?>) object).size() - 1){
                        System.out.println(((List<?>) object).get(i));
                        productQuery.addCloseParentheses();
                    } else {
                        productQuery.addOperator(SearchLogicalOperators.OR);
                    }

                    productQuery.addOperator(SearchLogicalOperators.AND);
                }
            }

            System.out.println(productQuery);
            return productQuery.toString();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

        private Set<String> getColumnsNames(ProductSearch productSearch){
        return productSearch.getMap().keySet();
    }

}
