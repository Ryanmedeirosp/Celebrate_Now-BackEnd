package com.celebrate.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.celebrate.backend.models.Budget;


@Repository
public interface BudgetRepository extends JpaRepository<Budget,Integer> {

    List<Budget> findAllByClientId(Integer idClient);

} 
