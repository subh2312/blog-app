package com.subhankar.blogappbackend.repositories;

import com.subhankar.blogappbackend.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
