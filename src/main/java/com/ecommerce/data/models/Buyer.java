package com.ecommerce.data.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Buyer extends Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String buyerId;
    @ManyToOne
    private Cart cart;

}
