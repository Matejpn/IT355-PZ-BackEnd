package com.project.it355pz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.it355pz.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	Category findByName(String name);
}
