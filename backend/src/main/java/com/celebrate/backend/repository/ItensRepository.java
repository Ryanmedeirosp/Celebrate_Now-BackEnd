package com.celebrate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.celebrate.backend.models.Item;

@Repository
public interface ItensRepository extends JpaRepository<Item,Integer> {

    
} 