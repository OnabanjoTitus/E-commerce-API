package com.ecommerce.services;

import com.ecommerce.data.models.*;
import com.ecommerce.web.exceptions.AccountCreationException;
import com.ecommerce.web.exceptions.AccountException;
import com.ecommerce.web.exceptions.ProductException;

import java.util.List;


public interface SellerServices {
    SellerDto addAccount(SellerRequestDto sellerRequestDto) throws AccountCreationException;
    List<SellerDto> findSellerByName(String sellerName) throws AccountException;
    List<ProductRequest> findProductsByName(String productName) throws ProductException;
    List<ProductRequest> findProductsBySellerName(String sellerName) throws ProductException, AccountException;
    Product sellerUploadsProduct(String loginToken, ProductDto productDto) throws AccountException, ProductException;
}
