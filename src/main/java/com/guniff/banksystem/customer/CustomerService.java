package com.guniff.banksystem.customer;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    //이름으로 고객 조회
    public Optional<Customer> findByName(String name) {

        validateName(name);

        return customerRepository.findByName(name);
    }

    //고객 생성
    public Customer join(String name) {
        Customer customer = new Customer();
        customer.setName(name);

        validateName(name);

        if (customerRepository.existsByName(name)) {
            throw new IllegalArgumentException("이미 존재하는 고객입니다.");
        }

        return customerRepository.save(customer);
    }

    // 공통 이름 검증
    private void validateName(String name) {
        if(name == null){
            throw new IllegalArgumentException("이름이 NULL값입니다.");
        }

        if(name.isEmpty()){
            throw new IllegalArgumentException("이름은 최소 1글자 이상이어야 합니다.");
        }

        if(name.length() > 20){
            throw new IllegalArgumentException("이름은 최대 20자 이하여야 합니다.");
        }

        if(!name.matches("^[A-Za-z0-9]+$")){
            throw new IllegalArgumentException("이름은 영어와 숫자로만 이루어져야 합니다.");
        }
    }

}
