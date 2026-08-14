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
    private Account senderAccountNumber;

    @Column(nullable = false)
    private String receiverAccountNumber;

    private Bank receiverBank;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private LocalDateTime transferDateTime;

    protected Transfer() {}

}
