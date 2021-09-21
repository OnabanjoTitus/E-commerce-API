package com.ecommerce.data.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Seller extends Customer {
    @Id
    private String BuyerId;
    @OneToMany
    private List<Product> products=new ArrayList<>();
}
