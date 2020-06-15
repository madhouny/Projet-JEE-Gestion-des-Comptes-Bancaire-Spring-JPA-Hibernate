package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {
 
}
