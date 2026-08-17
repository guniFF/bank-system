package com.guniff.banksystem.customer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("join: 이름은 최대 20자 이하여야 합니다.")
    public void joinTest1(){
        //given
        String name = "123456789012345678901";

        //when
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> customerService.join(name)
        );

        //then
        assertEquals("이름은 최대 20자 이하여야 합니다.", ex.getMessage());
    }

    @Test
    @DisplayName("join: 이름은 최소 1글자 이상이어야 합니다.")
    public void joinTest2(){
        //given
        String name = "";

        //when
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> customerService.join(name));

        //then
        assertEquals("이름은 최소 1글자 이상이어야 합니다.", ex.getMessage());
    }

    @Test
    @DisplayName("join: 이름은 null값이 될수 없습니다.")
    public void joinTest3(){
        //given
        String name = null;

        //when
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> customerService.join(name));

        //then
        assertEquals("이름이 NULL값입니다.", ex.getMessage());
    }

    @Test
    @DisplayName("join: 이름에 한글이 올수없습니다.")
    public void joinTest4_1(){
        //given
        String name = "홍길동";

        //when
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> customerService.join(name));

        //then
        assertEquals("이름은 영어와 숫자로만 이루어져야 합니다.", ex.getMessage());
    }

    @Test
    @DisplayName("join: 이름에 특수문자가 올수없습니다.")
    public void joinTest4_2(){
        //given
        String name = "testCustomer_";

        //when
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> customerService.join(name));

        //then
        assertEquals("이름은 영어와 숫자로만 이루어져야 합니다.", ex.getMessage());
    }

    @Test
    @DisplayName("join: 이미 존재하는 고객은 등록할 수 없다")
    public void joinTest5(){
        //given
        String name = "testCustomer";

        // 이미 등록된 고객이 존재한다고 가정
        Customer existing = new Customer();
        existing.setName(name);

        when(customerRepository.findByName(name)).thenReturn(Optional.of(existing));

        //when
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> customerService.join(name)
        );

        //then
        assertEquals("이미 존재하는 고객입니다.",  ex.getMessage());
    }

}