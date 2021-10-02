package com.ecommerce.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductDto {
    private String productName;
    private String productDescription;
    private Double productPrice;
    private MultipartFile productImage;
    private String productCategory;
}
