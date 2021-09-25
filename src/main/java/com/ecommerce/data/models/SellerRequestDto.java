package com.ecommerce.data.models;

import lombok.Data;

@Data
public class SellerRequestDto extends CustomerRequestDto {
    private String sellerName;
    private String sellerLocation;
}
