package com.ecommerce.services;

import com.ecommerce.data.models.Buyer;
import com.ecommerce.data.models.ProductRequest;
import com.ecommerce.data.models.Role;
import com.ecommerce.data.models.Seller;
import com.ecommerce.data.repository.ProductRepository;
import com.ecommerce.data.repository.SellerRepository;
import com.ecommerce.dtos.BuyerDto;
import com.ecommerce.dtos.BuyerRequestDto;
import com.ecommerce.dtos.SellerDto;
import com.ecommerce.web.exceptions.AccountException;
import com.ecommerce.web.exceptions.ProductException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;


import java.util.List;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@Slf4j
class BuyerServicesImplTest {
    @Mock
    SellerRepository sellerRepository;
    @Mock
    ProductRepository productRepository;



    @InjectMocks
    BuyerServicesImpl buyerServices = new BuyerServicesImpl();

    Seller seller;
    Buyer buyer;
    SellerDto sellerDto;
    BuyerRequestDto buyerRequestDto;
    BuyerDto buyerDto;
    ProductRequest productRequest;
    @BeforeEach
    void setUp() {
        productRequest= new ProductRequest();

        seller = new Seller();
        seller.setSellerName("test-name");

        sellerDto = new SellerDto();

        buyer = new Buyer();
        buyer.setBuyerEmailAddress("Test@gmail.com");
        buyer.setBuyerPassword("1234");
        buyer.setRole(Role.BUYER);

        buyerDto= new BuyerDto();

        buyerRequestDto= new BuyerRequestDto();
        buyerRequestDto.setBuyerEmailAddress("Test@gmail.com");
        buyerRequestDto.setBuyerPassword("1234");
        buyerRequestDto.setConfirmPassword("1234");




    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void findSellerByName() {
        //given
        String sellerName = "test-name";


        //when
        try {
            when(buyerServices.findSellerByName(sellerName)).thenReturn(List.of(sellerDto));
        } catch (AccountException accountException) {
            accountException.getMessage();
        } finally {
            //then
            verify(sellerRepository, times(1)).findAllSellerBySellerName(sellerName);
        }
    }



    @Test
    void findProductsByName() {
        //given
        String productName = "test-product-Name";


        //when
        try {
            when(buyerServices.findProductsByName(productName)).thenReturn(List.of(productRequest));
        } catch (ProductException productException) {
           productException.getMessage();
        } finally {
            //then
            verify(productRepository, times(1)).findAllByProductNameContains(productName);
        }
    }


    @Test
    void findProductsBySellerName() {
        //given
        String sellerName = "test-Name";


        //when
        try {
            when(buyerServices.findProductsBySellerName(sellerName)).thenReturn(List.of(productRequest));
        } catch (ProductException | AccountException productException) {
            productException.getMessage();
        } finally {
            //then
            verify(productRepository, times(1)).findAllBySellerSellerNameContains(sellerName);
        }
    }

}