package com.celebrate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.celebrate.backend.models.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer>{

    
} 
