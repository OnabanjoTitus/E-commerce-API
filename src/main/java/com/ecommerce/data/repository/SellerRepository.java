package com.ecommerce.data.repository;

import com.ecommerce.data.models.Buyer;
import com.ecommerce.data.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {

    Optional<List<Seller>>findAllSellerBySellerName(String SellerName);

    Optional<List<Seller>>findAllSellerBySellerNameContaining(String sellerName);

    Optional<Seller>findSellerBySellerEmailAddress(String sellerEmail);
}
