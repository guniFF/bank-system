package com.guniff.banksystem.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Customer {

    @Id
    @GeneratedValue // 추후 시퀀스 설정
    private Long customerId;

    @Column(nullable = false, length = 20)
    @NotBlank
    private String name;

    protected Customer() {}
}
