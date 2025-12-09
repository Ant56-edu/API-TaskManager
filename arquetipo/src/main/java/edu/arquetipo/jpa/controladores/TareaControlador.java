package edu.arquetipo.jpa.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.arquetipo.jpa.entidades.Tarea;
import edu.arquetipo.jpa.servicios.OperativaTareaInterfaz;

@RestController
@RequestMapping("/api/tarea")
public class TareaControlador {
    private final OperativaTareaInterfaz tareaInterfaz;

    @Autowired
    public TareaControlador(OperativaTareaInterfaz tareaInterfaz) {
        this.tareaInterfaz = tareaInterfaz;
    }

    // Create a new product.
    @PostMapping("/nueva")
    public ResponseEntity<Tarea> saveProduct(@RequestBody Tarea tarea) {
        Tarea nuevaTarea = tareaInterfaz.crearTarea(tarea);
        System.out.println(nuevaTarea.toString());
        return ResponseEntity.ok(nuevaTarea);
    }

    // Get all products.
    @GetMapping("/listado")
    public List<Tarea> getAllProducts() {
        List<Tarea> listaTareas = new ArrayList<>();
        for (Tarea tarea : listaTareas) {
            listaTareas.add(tareaInterfaz.buscarTarea(tarea.getId()));
        }
        return listaTareas;
    }

    // Get a product by ID.
    @GetMapping("/{id}")
    public ResponseEntity<Tarea> getTareaById(@PathVariable Long id) {
        Tarea tarea = tareaInterfaz.buscarTarea(id);
        if (tarea != null) {
            return new ResponseEntity<>(tarea, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    // Update a product by ID.
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Tarea> updateTarea(@PathVariable long id, @RequestBody Tarea tareaActualizada) {
        tareaActualizada = tareaInterfaz.editarDetalles(id, tareaActualizada);
        return ResponseEntity.ok(tareaActualizada);
    }

    // Delete a product by ID.
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> deleteTarea(@PathVariable Long id) {
        tareaInterfaz.borrarTarea(id);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
