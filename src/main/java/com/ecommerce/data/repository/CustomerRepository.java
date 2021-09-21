package com.ecommerce.data.repository;

import com.ecommerce.data.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Optional<Customer>findCustomerByEmailAddress(String email);
}
