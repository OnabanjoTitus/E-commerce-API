package com.ecommerce.data.models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer productId;
    @Column
    private String productName;
    @Column
    private String productDescription;
    @Column
    private BigDecimal productPrice;
    @Column
    private String productImage;
    @Column
    private String productCategory;

}
