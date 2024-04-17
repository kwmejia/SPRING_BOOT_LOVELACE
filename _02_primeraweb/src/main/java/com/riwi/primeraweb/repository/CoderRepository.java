package com.riwi.primeraweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.primeraweb.entity.Coder;

/**
 * El repositorio se encarga de toda la persistencia de los datos
 * Interactua directamente con la Base de datos.
 */
@Repository
/** Extendemos de la interfaz de JPA para obtener todos los métodos del CRUD */
public interface CoderRepository extends JpaRepository<Coder, Long> {

}