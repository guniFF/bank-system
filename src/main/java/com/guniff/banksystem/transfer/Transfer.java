package com.guniff.banksystem.transfer;

import com.guniff.banksystem.account.Account;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Transfer {

    @Id
    @GeneratedValue
    private Long transferId;

    @ManyToOne
    @JoinColumn(name = "sender_account_number")
    private Account fromAccountNumber;

    @ManyToOne
    @JoinColumn(name = "receiver_account_number")
    private Account toAccountNumber;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private LocalDateTime transferDateTime;

    protected Transfer() {}

}
