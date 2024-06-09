package com.e.web.commerce.Entity;

import com.e.web.commerce.Enum.OrderStatus;
import com.e.web.commerce.Enum.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "orders")
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client")
    private Client client;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;


    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Column(name = "total_amount")
    private Double totalAmount;


    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "orders")
    private List<OrderItems> orderItems;
}
