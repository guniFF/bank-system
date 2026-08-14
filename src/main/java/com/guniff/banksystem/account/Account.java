package com.guniff.banksystem.account;

import com.guniff.banksystem.customer.Customer;
import jakarta.persistence.*;

@Entity
public class Account {

    @Id
    @Column(nullable = false, length = 20)
    private String accountNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private Long balance;

    @Column(nullable = false)
    private Long dailyTransferLimit;

    @Column(nullable = false)
    private Long transferLimit;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status;

    protected Account() {
    }
}