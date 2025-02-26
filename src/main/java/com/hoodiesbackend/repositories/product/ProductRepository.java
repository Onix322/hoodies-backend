package com.hoodiesbackend.repositories.product;

import com.hoodiesbackend.entities.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM hoodie.product LIMIT :startFrom, :amount")
    List<Product> getAmountOfProducts(@Param("amount") int amount, @Param("startFrom") int startFrom);
}
