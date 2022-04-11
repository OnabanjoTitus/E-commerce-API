package com.ecommerce.services.EmailServices;

import com.ecommerce.dtos.BuyerDto;
import com.ecommerce.dtos.SellerDto;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    void sendMail(BuyerDto buyerDto);
    void sendMail(SellerDto sellerDto);

}
