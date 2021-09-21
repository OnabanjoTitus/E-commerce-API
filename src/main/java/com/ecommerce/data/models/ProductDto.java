package com.ecommerce.data.models;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductDto {
    private String productName;
    private String productDescription;
    private Double productPrice;
    private MultipartFile image;
    private String productCategory;
}
