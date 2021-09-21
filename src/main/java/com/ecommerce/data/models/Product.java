package com.ecommerce.data.models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private BigDecimal price;
    @Column
    private String image;
    @Column
    private String category;
    @Column
    private Integer productQuantity;

}
