package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
