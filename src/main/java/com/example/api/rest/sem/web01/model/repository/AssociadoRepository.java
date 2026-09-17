package com.example.api.rest.sem.web01.model.repository;

import com.example.api.rest.sem.web01.model.entity.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {
}
