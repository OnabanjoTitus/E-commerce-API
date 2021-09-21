package com.ecommerce.data.repository;

import com.ecommerce.data.models.Buyer;
import com.ecommerce.data.models.Customer;
import com.ecommerce.data.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository  extends JpaRepository<Seller,Integer> {
    Optional<Customer> findCustomerByEmailAddress(String email);
}
