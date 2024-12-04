package com.celebrate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.celebrate.backend.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer>{

    
} 
