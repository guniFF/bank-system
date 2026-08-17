package com.guniff.banksystem.account;

import com.guniff.banksystem.customer.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Account {


    @Id
    @GeneratedValue
    private Long accountId;

    @Column(nullable = false, length = 20,  unique = true)
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

    public Account(String accountNumber, Customer customer) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.balance = 0L;
        this.dailyTransferLimit = 5_000_000L;
        this.transferLimit = 5_000_000L;
        this.status = AccountStatus.ACTIVE;
    }

    public void deposit(Long amount) {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("입금액은 0보다 커야 합니다.");
        }
        this.balance += amount;
    }

    public void withdraw(Long amount) {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("출금액은 0보다 커야 합니다.");
        }
        if (this.balance < amount) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }
        this.balance -= amount;
    }
}