package com.sinensia.api.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;

/**
 * Esta clase representa a un cliente de la empresa.
 * 
 */

@Entity
@Table(name = "clientes", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
public class Cliente {
    // Esta anotación indica que esta clase es una entidad JPA.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // El campo id se generará automáticamente.

    private String nombre;

    @Email
    @Column(unique = true)
    private String email;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
