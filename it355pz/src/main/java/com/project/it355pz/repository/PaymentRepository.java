package com.project.it355pz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.it355pz.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
