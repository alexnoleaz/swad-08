package com.example.swad8.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.swad8.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
