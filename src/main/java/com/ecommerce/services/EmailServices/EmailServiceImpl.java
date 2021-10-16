package com.ecommerce.services.EmailServices;

import com.ecommerce.dtos.BuyerDto;
import com.ecommerce.dtos.SellerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Override
    public void sendMail(BuyerDto buyerDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("EcommerceByTee@commerce.com");
        message.setTo(buyerDto.getBuyerEmailAddress());
        message.setSubject("Account Created Successfully!!!!!!");
        message.setText("Dear customer your e-commerce by tee's buyer's account has been successfully set up");
        javaMailSender.send(message);
    }

    @Override
    public void sendMail(SellerDto sellerDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("EcommerceByTee@commerce.com");
        message.setTo(sellerDto.getSellerEmailAddress());
        message.setSubject("Account Created Successfully!!!!!!");
        message.setText("Dear customer your e-commerce by tee's seller's account has been successfully set up");
        javaMailSender.send(message);
    }

}
