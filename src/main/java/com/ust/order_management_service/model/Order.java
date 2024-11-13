package com.ust.order_management_service.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private OrderStatus status;
    private Double totalAmount;
    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
//        updatedAt = LocalDateTime.now();
    }
//    @PreUpdate
//    protected void onUpdate() {
//        updatedAt = LocalDateTime.now();
//    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderDTO")
    private List<OrderItem> items;
}