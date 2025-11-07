package com.example.banque.repositories;

import com.example.banque.entities.Compte;
import com.example.banque.entities.TypeCompte;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "comptes", collectionResourceRel = "comptes", itemResourceRel = "compte")
public interface CompteRepository extends JpaRepository<Compte, Long> {

    // ✅ Version simple (liste)
    @RestResource(path = "byType")
    List<Compte> findByType(@Param("t") TypeCompte type);

    // ✅ Version paginée (optionnelle mais pratique)
    @RestResource(path = "byTypePaged")
    Page<Compte> findByType(@Param("t") TypeCompte type, Pageable pageable);
}
