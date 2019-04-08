package com.project.it355pz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.it355pz.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer>{

	Brand findByName(String name);
}
