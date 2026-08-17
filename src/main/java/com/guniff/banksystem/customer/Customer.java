package com.guniff.banksystem.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue
    private Long customerId;

    @Column(nullable = false, length = 20, unique = true)
    @NotBlank
    private String name;

    protected Customer() {}

}
