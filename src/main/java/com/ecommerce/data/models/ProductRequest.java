package com.ecommerce.data.models;

import lombok.Data;


@Data
public class ProductRequest {
    private String productName;
    private String productDescription;
    private Double productPrice;
    private String productImage;
    private String productCategory;
}
