package com.sinensia.api.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Esta clase representa a un cliente de la empresa.
 * 
 */
@Entity
public class Cliente {
    // Esta anotación indica que esta clase es una entidad JPA.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // El campo id se generará automáticamente.

    private String nombre;
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

    // Constructores, getters y setters (omitiendo por brevedad).

    // Aquí puedes agregar métodos adicionales según tus necesidades.

    // Getters y setters para id, nombre y email (generados automáticamente o
    // definidos por ti).
}
