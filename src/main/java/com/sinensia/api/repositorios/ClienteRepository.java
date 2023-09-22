package com.sinensia.api.repositorios;

import com.sinensia.api.modelos.Cliente;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Esta interfaz define las operaciones de persistencia sobre la entidad
 * Cliente. Transactional es una anotación de Spring que indica que los métodos
 * de esta interfaz deben ejecutarse dentro de una transacción.
 * No es necesario crear metodos para las operaciones CRUD básicas ya que
 * JpaRepository ya las incluye.
 *
 */
@Transactional
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // No es necesario definir métodos aquí para las operaciones CRUD básicas.
}