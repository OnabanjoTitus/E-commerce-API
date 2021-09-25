package com.ecommerce.services;


import com.ecommerce.data.models.*;
import com.ecommerce.data.repository.BuyerRepository;
import com.ecommerce.data.repository.SellerRepository;
import com.ecommerce.web.exceptions.AccountCreationException;
import com.ecommerce.web.exceptions.AccountException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class BuyerServicesImplTest {

    @Autowired
    BuyerServices buyerServices;
    @Autowired
    BuyerRepository buyerRepository;
    @Autowired
    SellerServices sellerServices;

    BuyerRequestDto buyerRequestDto;
    @BeforeEach
    void setUp() {
        buyerRequestDto = new BuyerRequestDto();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Transactional
    void addAccount() throws AccountCreationException {
        buyerRequestDto.setBuyerEmailAddress("Tee");
        buyerRequestDto.setBuyerPassword("1234");
        buyerRequestDto.setConfirmPassword("1234");
        BuyerDto buyerDto=buyerServices.addAccount(buyerRequestDto);
        assertThat(buyerDto.getBuyerEmailAddress()).isEqualTo(buyerRequestDto.getBuyerEmailAddress());
        assertThat(buyerDto.getBuyerEmailAddress()).isEqualTo(buyerRepository.findBuyerByBuyerEmailAddress(buyerDto.getBuyerEmailAddress()).get().getBuyerEmailAddress());
        assertThat(buyerRepository.findBuyerByBuyerEmailAddress(buyerDto.getBuyerEmailAddress()).get().getRole()).isEqualTo(Role.BUYER);
    }

    @Test
    @Transactional
    void findSellerByName() throws AccountException, AccountCreationException {
        SellerRequestDto sellerRequestDto= new SellerRequestDto();
        sellerRequestDto.setSellerName("Sales");
        sellerRequestDto.setSellerEmailAddress("1234");
        sellerRequestDto.setSellerPassword("111");
        sellerRequestDto.setConfirmPassword("111");
        sellerServices.addAccount(sellerRequestDto);
        List<SellerDto> seller=buyerServices.findSellerByName("Sales");
        assertThat(seller).isNotNull();
        assertThat(seller).hasSize(1);
    }

    @Test
    void findProductsByName() throws AccountCreationException {
       //Todo:Complete test
    }

    @Test
    void findProductsBySellerName() {
    }

    @Test
    void updateAccount() {
    }
}