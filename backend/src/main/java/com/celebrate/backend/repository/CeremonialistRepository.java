package com.celebrate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.celebrate.backend.models.Ceremonialist;

@Repository
public interface CeremonialistRepository  extends JpaRepository<Ceremonialist,Integer>{

    
}