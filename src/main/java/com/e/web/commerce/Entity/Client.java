package com.e.web.commerce.Entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "last_name")
    private String lastName;


    @NotBlank(message = "FirstName is required")
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "birth_day")
    private LocalDate birthDay;

    @Column(unique = true)
    @NotBlank(message = "Email is required")
    private String email;

    @Column(name = "payment_method")
    private String paymentMethod;

    private String country;

    private String city;

    private String street;


    @Column(name = "postal_code")
    private Integer postalCode;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
