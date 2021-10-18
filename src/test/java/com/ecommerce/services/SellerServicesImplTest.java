package com.ecommerce.services;

import com.ecommerce.data.models.ProductRequest;
import com.ecommerce.data.repository.ProductRepository;
import com.ecommerce.data.repository.SellerRepository;
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

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class SellerServicesImplTest {
    @Mock
    SellerRepository sellerRepository;
    @Mock
    ProductRepository productRepository;



    @InjectMocks
    SellerServicesImpl sellerServices = new SellerServicesImpl();

    SellerDto sellerDto;
    ProductRequest productRequest;
    @BeforeEach
    void setUp() {
        sellerDto = new SellerDto();
        productRequest= new ProductRequest();
    }

    @AfterEach
    void tearDown() {
    }



    @Test
    void findSellerByName() {

        String sellerName = "test-name";


        //when
        try {
            when(sellerServices.findSellerByName(sellerName)).thenReturn(List.of(sellerDto));
        } catch (AccountException accountException) {
            accountException.getMessage();
        } finally {
            //then
            verify(sellerRepository, times(1)).findAllSellerBySellerName(sellerName);
        }
    }

    @Test
    void findProductsByName() {
        String productName = "test-product-Name";


        //when
        try {
            when(sellerServices.findProductsByName(productName)).thenReturn(List.of(productRequest));
        } catch (ProductException productException) {
            productException.getMessage();
        } finally {
            //then
            verify(productRepository, times(1)).findAllByProductNameContains(productName);
        }
    }

    @Test
    void findProductsBySellerName() {
        String sellerName = "test-Name";

        //when
        try {
            when(sellerServices.findProductsBySellerName(sellerName)).thenReturn(List.of(productRequest));
        } catch (ProductException | AccountException productException) {
            productException.getMessage();
        } finally {
            //then
            verify(productRepository, times(1)).findAllBySellerSellerNameContains(sellerName);
        }
    }


}