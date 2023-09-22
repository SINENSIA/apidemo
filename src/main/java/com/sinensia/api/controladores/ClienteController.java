package com.sinensia.api.controladores;

import com.sinensia.api.modelos.Cliente;
import com.sinensia.api.repositorios.ClienteRepository;
import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// TODO: Cambiar por imports específicos
import org.springframework.web.bind.annotation.*;

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

    /**
     * Este método devuelve una lista de todos los clientes.
     * Como demo vwamos a devolver un Map con una clave "clientes" y el valor
     * será la lista de clientes.
     * 
     * /**
     * Este método devuelve una lista de todos los clientes.
     * Como demo vamos a devolver un Map con una clave "clientes" y el valor
     * será la lista de clientes.
     *
     * @see cheatsheetseries.owasp.org cheatsheets
     *      AJAX_Security_Cheat_Sheet.html#server-side
     * @return Map
     */

    @GetMapping
    public Map<String, List<Cliente>> listarClientes() {

        List<Cliente> clientes = clienteRepository.findAll();
        Map<String, List<Cliente>> response = new HashMap<>();
        response.put("clientes", clientes);
        return response;
    }

    /**
     * Este método devuelve un cliente por su id.
     *
     * @param id Identificador del cliente a obtener
     * @return ResponseEntity
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {

        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Este método crea uno o varios clientes.
     *
     * @param clientes Lista de clientes a crear
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<Map<String, List<Cliente>>> crearClientes(
            @RequestBody List<Cliente> clientes) {
        try {
            // Intenta crear el cliente
            clienteRepository.saveAll(clientes);

            Map<String, List<Cliente>> response = new HashMap<>();
            response.put("clientes", clientes);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (ConstraintViolationException | DataIntegrityViolationException e) {
            // Maneja la excepción de restricción única
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

    }

    /**
     * Este método actualiza un nuevo cliente.
     *
     * @param id                 Identificador del cliente a actualizar
     * @param clienteActualizado Cliente con los datos actualizados
     * @return Cliente
     */
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

    /**
     * Este método actualiza parcialmente un cliente.
     *
     * @param id                 Identificador del cliente a actualizar
     * @param camposActualizados Mapa con los campos a actualizar
     * @return Cliente
     */
    @PatchMapping("/{id}")
    public Cliente actualizarClienteParcialmente(@PathVariable Long id,
            @RequestBody Map<String, Object> camposActualizados) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // Actualiza los campos proporcionados
        if (camposActualizados.containsKey("nombre")) {
            cliente.setNombre((String) camposActualizados.get("nombre"));
        }
        if (camposActualizados.containsKey("email")) {
            cliente.setEmail((String) camposActualizados.get("email"));
        }

        // Guarda el cliente actualizado en la base de datos
        return clienteRepository.save(cliente);
    }

}
