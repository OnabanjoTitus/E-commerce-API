package com.ecommerce.data.models;

import lombok.Data;

@Data
public class BuyerDto {
    private String buyerEmailAddress;

    public BuyerDto(String buyerEmailAddress) {
        this.buyerEmailAddress=buyerEmailAddress;
    }
}
