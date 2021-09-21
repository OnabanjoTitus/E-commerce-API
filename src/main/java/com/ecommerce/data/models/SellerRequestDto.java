package com.ecommerce.data.models;

import lombok.Data;

@Data
public class SellerRequestDto extends BuyerRequestDto {
    private String sellerId;
    private String sellerName;
    private String sellerLocation;
}
