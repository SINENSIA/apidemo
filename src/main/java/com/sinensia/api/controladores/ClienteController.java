package com.sinensia.api.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sinensia.api.modelos.Cliente;
import com.sinensia.api.repositorios.ClienteRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Esta clase define los endpoints para las operaciones CRUD sobre la entidad
 * Cliente. Implementa un controlador REST con los métodos necesarios para
 * listar, obtener, crear, actualizar y eliminar clientes.
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public Map<String, List<Cliente>> listarClientes() {

        List<Cliente> clientes = clienteRepository.findAll();
        Map<String, List<Cliente>> response = new HashMap<>();
        response.put("clientes", clientes);
        return response;
    }

    @GetMapping("/{id}")
    public Cliente obtenerClientePorId(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    @PostMapping
    public List<Cliente> crearClientes(@RequestBody List<Cliente> clientes) {
        return clienteRepository.saveAll(clientes);

    }

    @PutMapping("/{id}")
    public Cliente actualizarCliente(
            @PathVariable Long id, @RequestBody Cliente clienteActualizado) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        clienteExistente.setNombre(clienteActualizado.getNombre());
        clienteExistente.setEmail(clienteActualizado.getEmail());

        return clienteRepository.save(clienteExistente);
    }

    @DeleteMapping("/{id}")
    public void eliminarCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
    }

}
