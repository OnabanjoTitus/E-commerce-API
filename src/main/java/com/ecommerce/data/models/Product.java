package com.ecommerce.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn
    private Seller seller;

}
