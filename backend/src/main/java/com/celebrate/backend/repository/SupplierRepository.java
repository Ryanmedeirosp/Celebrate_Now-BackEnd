package com.celebrate.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.celebrate.backend.models.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer>{

    Optional<Supplier> findByCnpj(String cnpj);

    List<Supplier> findAllByCeremonialistId(Integer idCeremonialist);
} 
