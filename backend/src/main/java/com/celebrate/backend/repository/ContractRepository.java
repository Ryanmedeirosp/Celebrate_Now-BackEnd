package com.celebrate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.celebrate.backend.models.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer>{

    
} 