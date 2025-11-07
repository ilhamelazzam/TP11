package com.example.banque.repositories;

import com.example.banque.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "clients")
public interface ClientRepository extends JpaRepository<Client, Long> {
}
