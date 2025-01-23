package com.example.controllers;

import com.example.entities.Ciudadano;
import com.example.exceptions.CiudadanoExcepciones;
import com.example.persistence.GenericoJPA;

import java.util.List;
import java.util.Optional;

public class CiudadanoController {

    private final GenericoJPA<Ciudadano, Long> ciudadanoJPA;

    public CiudadanoController() {
        this.ciudadanoJPA = new GenericoJPA<>(Ciudadano.class);
    }

    // Para crear un ciudadano

    public void createCiudadano(String nombre, String apellido, String dni)
            throws CiudadanoExcepciones.CiudadanoDuplicadoException, CiudadanoExcepciones.CiudadanoCreadoException {

        // Verifica si ya existe un ciudadano con el mismo DNI

        if (ciudadanoJPA.findAllGenerico().stream().anyMatch(c -> c.getDni().equals(dni))) {
            throw new CiudadanoExcepciones.CiudadanoDuplicadoException("Ya existe un ciudadano con el DNI " + dni);
        }

        // Crea el objeto Ciudadano y lo guarda en la base de datos

        Ciudadano ciudadano = new Ciudadano(nombre, apellido, dni);
        try {
            // Intentamos crear el ciudadano

            ciudadanoJPA.createGenerico(ciudadano);
            System.out.println("Ciudadano creado exitosamente.");

        } catch (Exception e) {
            // Si ocurre un error al crear el ciudadano, lanzamos una excepción personalizada

            throw new CiudadanoExcepciones.CiudadanoCreadoException("Error al crear ciudadano: " + e.getMessage());
        }
    }

    // Para mostrar todos los Ciudadanos

    public List<Ciudadano> findAllCiudadanos() {
        return ciudadanoJPA.findAllGenerico();
    }

    // Para encontrar a un ciudadano por su ID (se usa en TurnoController)

    public Optional<Ciudadano> findCiudadanoById(Long id) {
        return ciudadanoJPA.findOneGenerico(id);
    }

    // Para actualizar un ciudadano (metodo mas simple)

    public void updateCiudadano(Ciudadano ciudadano) throws CiudadanoExcepciones.CiudadanoActualizadoException {
        try {
            // Intentamos actualizar el ciudadano

            ciudadanoJPA.updateGenerico(ciudadano);
            System.out.println("Ciudadano actualizado exitosamente.");

        } catch (Exception e) {
            // Si ocurre un error al actualizarlo, lanzamos una excepción personalizada

            throw new CiudadanoExcepciones.CiudadanoActualizadoException("No se ha podido actualizar el ciudadano: " + e.getMessage());
        }
    }

    // Para eliminar un ciudadano (metodo mas simple)

    public void deleteCiudadano(Long id) throws CiudadanoExcepciones.CiudadanoEliminadoException {
        try {
            // Intentamos eliminar el ciudadano

            ciudadanoJPA.deleteGenerico(id);
            System.out.println("Ciudadano eliminado exitosamente.");

        } catch (Exception e) {
            // Si ocurre un error al eliminarlo, lanzamos una excepción personalizada

            throw new CiudadanoExcepciones.CiudadanoEliminadoException("No se ha podido eliminar el ciudadano: " + e.getMessage());
        }
    }
}
