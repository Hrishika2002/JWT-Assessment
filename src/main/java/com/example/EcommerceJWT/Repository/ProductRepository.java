package com.example.EcommerceJWT.Repository;

import com.example.EcommerceJWT.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByName(String name);

}
