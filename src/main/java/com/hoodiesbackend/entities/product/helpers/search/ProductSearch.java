package com.hoodiesbackend.entities.product.helpers.search;

import com.hoodiesbackend.entities.product.helpers.ProductColor;
import com.hoodiesbackend.entities.product.helpers.Size;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, Object> getMap(){

        HashMap<String, Object> map = new HashMap<>();

        map.put("id", this.id);
        map.put("title", this.title);
        map.put("numberReviews", this.numberReviews);
        map.put("availableForPurchase", this.availableForPurchase);
        map.put("price", this.price);
        map.put("productColor", this.productColor);
        map.put("description", this.description);
        map.put("rating", this.rating);
        map.put("size", this.size);

        return map;
    }
}
