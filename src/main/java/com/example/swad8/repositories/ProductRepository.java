package com.example.swad8.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.swad8.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
