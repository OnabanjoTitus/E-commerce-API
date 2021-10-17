package com.ecommerce.data.repository;

import com.ecommerce.data.models.Buyer;
import com.ecommerce.data.models.Product;
import com.ecommerce.data.models.Role;
import com.ecommerce.data.models.Seller;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class SellerRepositoryTest {
    @Autowired
    SellerRepository sellerRepository;
    Seller seller;
    @BeforeEach
    void setUp() {
        seller= new Seller();
        seller.setSellerName("Titus");
        seller.setSellerPassword("1234");
        seller.setSellerLocation("Nigeria");
        seller.setSellerEmailAddress("Test@gmail.com");
        seller.addProduct(new Product());
        seller.setRole(Role.SELLER);
        sellerRepository.save(seller);
    }

    @AfterEach
    void tearDown() {
        seller= null;
    }

    @Test
    void findAllSellerBySellerName() {
       int size=sellerRepository.findAllSellerBySellerName("Titus").orElse(null).size();
       assertThat(size).isEqualTo(1);
    }

    @Test
    void findAllSellerBySellerNameContaining() {
        int size=sellerRepository.findAllSellerBySellerNameContaining("T").orElse(null).size();
        assertThat(size).isEqualTo(1);
    }

    @Test
    void findSellerBySellerEmailAddress() {
        Seller seller1=sellerRepository.findSellerBySellerEmailAddress("Test@gmail.com").orElse(null);
        assertThat(seller1).isNotNull();
        assertThat(seller1).isEqualTo(seller);
    }
}