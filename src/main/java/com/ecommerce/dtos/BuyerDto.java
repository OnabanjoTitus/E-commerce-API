package com.ecommerce.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BuyerDto {
    private String buyerEmailAddress;
    public BuyerDto(String buyerEmailAddress) {
        this.buyerEmailAddress=buyerEmailAddress;
    }

}
