package com.ust.order_management_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    private Long productId;
    private String productName;
    private Integer quantity;
    private Double price;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "order_id")
    private Order order;
}