package com.moviles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moviles.model.entity.Modelo;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {

}
