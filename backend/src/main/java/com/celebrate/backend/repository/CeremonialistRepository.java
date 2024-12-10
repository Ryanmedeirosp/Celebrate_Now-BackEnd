package com.celebrate.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.celebrate.backend.models.Ceremonialist;

@Repository
public interface CeremonialistRepository extends JpaRepository<Ceremonialist, Integer>{

    Optional<Ceremonialist> findByEmail(String email);
}