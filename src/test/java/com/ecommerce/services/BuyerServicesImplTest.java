package com.ecommerce.services;


import com.ecommerce.data.models.*;
import com.ecommerce.data.repository.BuyerRepository;
import com.ecommerce.dtos.BuyerDto;
import com.ecommerce.dtos.BuyerRequestDto;
import com.ecommerce.dtos.SellerDto;
import com.ecommerce.dtos.SellerRequestDto;
import com.ecommerce.web.exceptions.AccountCreationException;
import com.ecommerce.web.exceptions.AccountException;
import com.ecommerce.web.exceptions.ProductException;
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

//    @Autowired
//    BuyerServices buyerServices;
//    @Autowired
//    BuyerRepository buyerRepository;
//    @Autowired
//    SellerServices sellerServices;
//
//    BuyerRequestDto buyerRequestDto;
//    @BeforeEach
//    void setUp() {
//        buyerRequestDto = new BuyerRequestDto();
//
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    @Transactional
//    void addAccount() throws AccountCreationException {
//        buyerRequestDto.setBuyerEmailAddress("Tee");
//        buyerRequestDto.setBuyerPassword("1234");
//        buyerRequestDto.setConfirmPassword("1234");
//        BuyerDto buyerDto=buyerServices.addAccount(buyerRequestDto);
//        assertThat(buyerDto.getBuyerEmailAddress()).isEqualTo(buyerRequestDto.getBuyerEmailAddress());
//        assertThat(buyerDto.getBuyerEmailAddress()).isEqualTo(buyerRepository.findBuyerByBuyerEmailAddress(buyerDto.getBuyerEmailAddress()).get().getBuyerEmailAddress());
//        assertThat(buyerRepository.findBuyerByBuyerEmailAddress(buyerDto.getBuyerEmailAddress()).get().getRole()).isEqualTo(Role.BUYER);
//    }
//
//    @Test
//    @Transactional
//    void findSellerByName() throws AccountException, AccountCreationException {
//        SellerRequestDto sellerRequestDto= new SellerRequestDto();
//        sellerRequestDto.setSellerName("Sales");
//        sellerRequestDto.setSellerEmailAddress("1234");
//        sellerRequestDto.setSellerPassword("111");
//        sellerRequestDto.setConfirmPassword("111");
//        sellerServices.addAccount(sellerRequestDto);
//        List<SellerDto> seller=buyerServices.findSellerByName("Sales");
//        assertThat(seller).isNotNull();
//        assertThat(seller).hasSize(1);
//    }
//
//    @Test
//    @Transactional
//    void findProductsByName() throws AccountCreationException, ProductException {
//    List<ProductRequest>productList= buyerServices.findProductsByName("jein");
//    assertThat(productList).isEmpty();
//    //Todo more tests
//    }
//
//    @Test
//    @Transactional
//    void findProductsBySellerName() {
//        //Todo more tests
//    }
//
//    @Test
//    @Transactional
//    void updateAccount() {
//        //Todo more tests
//    }
}