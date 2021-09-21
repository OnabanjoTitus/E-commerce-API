package com.ecommerce.services;

import com.ecommerce.data.models.*;
import com.ecommerce.web.exceptions.AccountCreationException;
import com.ecommerce.web.exceptions.AccountException;

import java.util.List;


public interface BuyerServices {
    BuyerRequestDto addAccount(BuyerRequestDto buyerRequestDto) throws AccountCreationException;
    List<SellerDto> findSellerByName(String sellerName);
    List<ProductRequest> findProductsByName(String productName);
    List<ProductRequest> findProductsBySellerName(String sellerName);
    CustomerUpdateDto updateAccount(CustomerUpdateDto customerUpdateDto) throws AccountException;
}
