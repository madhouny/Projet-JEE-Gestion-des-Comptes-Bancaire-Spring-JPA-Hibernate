package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, String> {

}
