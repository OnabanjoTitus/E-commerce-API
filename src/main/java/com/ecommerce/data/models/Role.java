package com.ecommerce.data.models;

public enum Role {
    BUYER,SELLER;

    @Override
    public String toString() {
        switch (this){
            case BUYER:return "Buyer";
            case SELLER:return "Seller";
            default: return null;
        }

    }
}
