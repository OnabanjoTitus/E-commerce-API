package com.ecommerce.data.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Buyer extends Customer {
    @Id
    private String BuyerId;

    @ManyToOne
    private Cart cart;

}
