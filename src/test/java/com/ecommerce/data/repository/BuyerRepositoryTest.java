package com.ecommerce.data.repository;

import com.ecommerce.data.models.Buyer;
import com.ecommerce.data.models.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BuyerRepositoryTest {
    @Autowired
    BuyerRepository buyerRepository;
    Buyer buyer;
    @BeforeEach
    void setUp() {
        buyer= new Buyer();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findBuyerByBuyerEmailAddress() {
        buyer.setBuyerEmailAddress("Test@gmail.com");
        buyer.setBuyerPassword("1234");
        buyer.setRole(Role.BUYER);
        buyerRepository.save(buyer);
        Buyer buyerFound=buyerRepository.findBuyerByBuyerEmailAddress("Test@gmail.com").orElse(null);
        assertThat(buyerFound).isNotNull();
        assertThat(buyerFound).isEqualTo(buyer);

    }
}