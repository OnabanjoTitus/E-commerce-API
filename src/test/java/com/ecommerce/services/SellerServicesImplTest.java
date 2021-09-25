package com.ecommerce.services;

import com.ecommerce.data.models.*;
import com.ecommerce.data.repository.BuyerRepository;
import com.ecommerce.data.repository.SellerRepository;
import com.ecommerce.web.exceptions.AccountCreationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SellerServicesImplTest {

    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    SellerServices sellerServices;

    SellerRequestDto sellerRequestDto;
    @BeforeEach
    void setUp() {
        sellerRequestDto = new SellerRequestDto();

    }


    @AfterEach
    void tearDown() {
    }

    @Test
    @Transactional
    void addAccount() throws AccountCreationException {
        sellerRequestDto.setSellerName("Sales");
        sellerRequestDto.setSellerEmailAddress("1234");
        sellerRequestDto.setSellerPassword("111");
        sellerRequestDto.setConfirmPassword("111");
        SellerDto sellerDto=  sellerServices.addAccount(sellerRequestDto);
        assertThat(sellerDto.getSellerEmailAddress()).isEqualTo(sellerRequestDto.getSellerEmailAddress());
        assertThat(sellerRepository.findAllSellerBySellerName(sellerDto.getSellerName()).get().getRole()).isEqualTo(Role.SELLER);
    }

    @Test
    @Transactional
    void findSellerByName() throws AccountCreationException {
        sellerRequestDto.setSellerName("Sales");
        sellerRequestDto.setSellerEmailAddress("1234");
        sellerRequestDto.setSellerPassword("111");
        sellerRequestDto.setConfirmPassword("111");
        SellerDto sellerDto=  sellerServices.addAccount(sellerRequestDto);
        assertThat(sellerDto.getSellerEmailAddress()).isEqualTo(sellerRequestDto.getSellerEmailAddress());
        assertThat(sellerDto.getSellerEmailAddress()).isEqualTo(sellerRepository.findAllSellerBySellerName(sellerDto.getSellerName()).get().getSellerEmailAddress());
        assertThat(sellerRepository.findAllSellerBySellerName(sellerDto.getSellerName()).get().getRole()).isEqualTo(Role.SELLER);
    }

    @Test
    void findProductsByName() {

    }

    @Test
    void findProductsBySellerName() {
    }

    @Test
    void sellerUploadsProduct() {
    }
}