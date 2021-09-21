package com.ecommerce.data.models;

import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
public class SellerDto extends Customer {
    private String sellerName;
    private String sellerLocation;
    private List<Product> products=new ArrayList<>();
}
