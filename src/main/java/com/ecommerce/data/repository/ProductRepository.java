package com.ecommerce.data.repository;


import com.ecommerce.data.models.Product;
import com.ecommerce.data.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository  extends JpaRepository<Product, String> {

    Optional<List<Product>> findAllByProductNameContains(String name);
    Optional<List<Product>> findAllBySellerSellerNameContains(String name);

}

