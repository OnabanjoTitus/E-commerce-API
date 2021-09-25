package com.ecommerce.services;

import com.ecommerce.data.models.*;

import java.util.List;

public interface SellerServices {
    CustomerRequestDto addAccount(SellerRequestDto sellerRequestDto);
    List<Customer> findSellerByName(String sellerName);
    List<Product> findProductsByName(String productName);
    List<Product> findProductsBySellerName(String sellerName);
    CustomerUpdateDto updateAccount(SellerRequestDto sellerRequestDto);
    Product sellerUploadsProduct(String loginToken, ProductDto productDto);
}
