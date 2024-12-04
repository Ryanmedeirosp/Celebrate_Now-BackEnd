package com.celebrate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.celebrate.backend.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {

    
} 