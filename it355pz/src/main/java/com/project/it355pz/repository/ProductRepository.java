package com.project.it355pz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.it355pz.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	List<Product> findByisDeletedFalse();
	
	@Query("SELECT p from Product p WHERE p.isDeleted = :isDeleted and p.category.name = :category")
	List<Product> findByCategory(@Param("isDeleted") boolean isDeleted, @Param("category") String category); 
	


}
