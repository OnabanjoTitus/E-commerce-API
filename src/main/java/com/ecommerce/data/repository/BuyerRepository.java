package com.ecommerce.data.repository;

import com.ecommerce.data.models.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer,Integer> {


   Optional<Buyer>findBuyerByBuyerEmailAddress(String emailAddress);
}

