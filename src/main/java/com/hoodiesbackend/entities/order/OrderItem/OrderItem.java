package com.hoodiesbackend.entities.order.OrderItem;

import com.hoodiesbackend.entities.order.Order;
import com.hoodiesbackend.entities.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_item")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "initial_price")
    private Double initialPrice;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Order order;
}
