package com.hoodiesbackend.entities.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hoodiesbackend.entities.order.OrderItem.OrderItem;
import com.hoodiesbackend.entities.order.helpers.StatusOrder;
import com.hoodiesbackend.entities.user.User;
import com.hoodiesbackend.entities.user.address.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Order Status mandatory")
    private StatusOrder statusOrder;

    @Column(name = "total_price")
    @NotNull(message = "Total price is mandatory")
    private Double totalPrice;

    @Column(name = "comments")
    private String comments;

    @Column(name = "created_at")
    @NotNull(message = "Created at is mandatory")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "finalized_at")
    private LocalDateTime finalizedAt;

    @ManyToOne
    @NotNull(message = "User mandatory")
    private User user;

    @ManyToOne
    private Address address;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}
