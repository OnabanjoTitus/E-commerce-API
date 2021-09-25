package com.ecommerce.data.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class Customer {

    private Integer customerId;
    private String customerEmailAddress;
    private String customerPassword;
    private Role customerRole;



}
