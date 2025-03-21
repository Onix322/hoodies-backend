package com.hoodiesbackend.entities.product.helpers.search;

import com.hoodiesbackend.entities.product.helpers.ProductColor;
import com.hoodiesbackend.entities.product.helpers.Size;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ProductSearch {

    private Long id;
    private List<String> title;
    private Integer numberReviews;
    private Boolean availableForPurchase;
    private Double price;
    private List<ProductColor> productColor;
    private String description;
    private Integer rating;
    private List<Size> size;
}
