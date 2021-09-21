package com.ecommerce.data.models;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import java.math.BigDecimal;
@Data
public class ProductDto {
    private String productName;
    private String productDescription;
    private Double productPrice;
    private MultipartFile image;
    private String productCategory;
}
