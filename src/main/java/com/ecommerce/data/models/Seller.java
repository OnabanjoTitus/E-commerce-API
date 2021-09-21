package com.ecommerce.data.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Seller extends Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String sellerId;
    private String sellerName;
    private String sellerLocation;
    @OneToMany
    private List<Product> products=new ArrayList<>();
}
