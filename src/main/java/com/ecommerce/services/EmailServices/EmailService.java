package com.ecommerce.services.EmailServices;

import com.ecommerce.data.models.Product;

import com.ecommerce.dtos.BuyerDto;
import com.ecommerce.dtos.SellerDto;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface EmailService {
    void sendMail(BuyerDto buyerDto);
    void sendMail(SellerDto sellerDto);
}
