package com.ust.order_management_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
    private List<OrderItem> items;

    @PrePersist
    protected void setParent() {
        createdAt = LocalDateTime.now();
        if (items != null) {
            for (OrderItem item : items) {
                item.setOrder(this);
            }
        }
    }
}