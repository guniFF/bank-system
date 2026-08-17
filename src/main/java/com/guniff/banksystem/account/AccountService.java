package com.guniff.banksystem.account;

import com.guniff.banksystem.customer.Customer;
import com.guniff.banksystem.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;

    // 계좌 조회
    public Optional<Account> findByNameAndAccountNumber(String customer_name, String account_number) {
        Customer customer = customerService.findByName(customer_name).orElse(null);

        if (customer == null) {
            return Optional.empty();
        }

        return accountRepository.findByCustomerAndAccountNumber(customer, account_number);
    }

    // 계좌 등록
    public Account createAccount(String customer_name) {

        Customer customer = customerService.findByName(customer_name).orElseThrow(
                () -> new IllegalArgumentException("고객이 존재하지 않습니다."));

        String account_number = generateUniqueAccountNumber();

        if (accountRepository.existsByCustomer(customer)) {
            throw new IllegalArgumentException("이미 존재하는 계좌번호입니다.");
        }

        Account account = new Account(account_number, customer);
        return accountRepository.save(account);
    }

    // 계좌번호 생성
    private String generateUniqueAccountNumber() {
        String accountNumber;
        do {
            accountNumber = generateAccountNumber();
        }
        while (accountRepository.existsByAccountNumber(accountNumber));
        return accountNumber;
    }

    // 11자리 랜덤 숫자 문자열 생성
    private String generateAccountNumber() {
        long random = ThreadLocalRandom.current().nextLong(0L, 100_000_000_000L);
        return String.format("%011d", random);
    }

    // 계좌 입금
    public Account deposit(String customer_name, String account_number, Long deposit_amount) {
        Account account = findByNameAndAccountNumber(customer_name, account_number).orElseThrow(
                () -> new IllegalArgumentException("계좌를 찾을 수 없습니다.")
        );

        account.deposit(deposit_amount);
        return accountRepository.save(account);
    }

    // 계좌 출금
    public Account withdraw(String customerName, String accountNumber, Long withdrawAmount) {
        Account account = findByNameAndAccountNumber(customerName, accountNumber).orElseThrow(
                () -> new IllegalArgumentException("계좌를 찾을 수 없습니다.")
        );

        account.withdraw(withdrawAmount);
        return accountRepository.save(account);
    }
}


