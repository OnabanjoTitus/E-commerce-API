package com.ecommerce.data.repository;


import com.ecommerce.data.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface ProductRepository  extends JpaRepository<Product, String> {

    Optional<Product> findAllByProductNameContains(String name);
    Optional<Product> findAllBySellerSellerNameContains(String name);

}

