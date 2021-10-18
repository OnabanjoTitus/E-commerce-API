package com.ecommerce.data.repository;

import com.ecommerce.data.models.Product;
import com.ecommerce.data.models.Role;
import com.ecommerce.data.models.Seller;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@Transactional
class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;
    Seller seller;
    Product product;
    @BeforeEach
    void setUp() {
        seller= new Seller();
        seller.setSellerName("Titus");
        seller.setSellerPassword("1234");
        seller.setSellerLocation("Nigeria");
        seller.setSellerEmailAddress("Test@gmail.com");
        seller.addProduct(new Product());
        seller.setRole(Role.SELLER);
        product= new Product();
        product.setSeller(seller);
        product.setProductPrice(BigDecimal.valueOf(100));
        product.setProductName("Test-Shirt");
        product.setProductDescription("Nice shirt");
        product.setProductImage("Test-Image");
        product.setProductCategory("shirts");
        productRepository.save(product);
    }

    @AfterEach
    void tearDown() {
        product=null;
    }

    @Test
    void findAllByProductNameContains() {
        int product=productRepository.findAllByProductNameContains("Shirt").orElse(null).size();
        assertThat(product).isEqualTo(1);
    }

    @Test
    void findAllBySellerSellerNameContains() {
        int product=productRepository.findAllBySellerSellerNameContains("T").orElse(null).size();
        assertThat(product).isEqualTo(1);
    }
}