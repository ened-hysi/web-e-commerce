package com.e.web.commerce.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDto {


    private String lastName;

    @NotBlank(message = "FirstName is required")
    private String firstName;

    private LocalDate birthDay;
    @NotBlank(message = "Email is required")
    private String email;

    private String paymentMethod;

    private String country;

    private String city;

    private String street;

    private Integer postalCode;

}
