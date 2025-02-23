package com.hoodiesbackend.entities.order;

import com.hoodiesbackend.entities.order.OrderItem.OrderItem;
import com.hoodiesbackend.entities.order.helpers.StatusOrder;
import com.hoodiesbackend.entities.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name ="orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name ="order_status")
    @Enumerated(EnumType.STRING)
    private StatusOrder statusOrder;

    @Column(name = "total_price")
    private Double totalPrice;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}
