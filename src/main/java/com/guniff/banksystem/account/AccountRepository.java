package com.guniff.banksystem.account;

import com.guniff.banksystem.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByCustomerAndAccountNumber(Customer customer, String accountNumber);

    boolean existsByCustomer(Customer customer);

    boolean existsByAccountNumber(String accountNumber);
}
