package com.example.controllers;

import com.example.entities.Turno;
import com.example.entities.Ciudadano;
import com.example.exceptions.TurnoExcepciones;
import com.example.exceptions.CiudadanoExcepciones;
import com.example.persistence.GenericoJPA;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TurnoController {

    private final GenericoJPA<Turno, Long> turnoJPA;
    private final GenericoJPA<Ciudadano, Long> ciudadanoJPA;

    public TurnoController() {
        this.turnoJPA = new GenericoJPA<>(Turno.class);
        this.ciudadanoJPA = new GenericoJPA<>(Ciudadano.class);
    }

    // Para crear un turno

    public void createTurno(Integer numero, String descripcion, Ciudadano ciudadano, Turno.Estado estado, java.util.Date fecha)
            throws TurnoExcepciones.TurnoDuplicadoException, CiudadanoExcepciones.CiudadanoNoExistenteException, TurnoExcepciones.TurnoCreadoException {

        // Verifica si el ciudadano es válido o existe

        if (ciudadano == null) {
            throw new CiudadanoExcepciones.CiudadanoNoExistenteException("Lo sentimos, el ciudadano no ha sido encontrado.");
        }

        // Verifica si el número de turno ya existe

        if (turnoJPA.findAllGenerico().stream().anyMatch(t -> t.getNumero().equals(numero))) {
            throw new TurnoExcepciones.TurnoDuplicadoException("Ya existe un turno con el número " + numero);
        }

        // Crea el objeto Turno y lo guarda en la base de datos

        Turno turno = new Turno(numero, fecha, descripcion, ciudadano, estado);
        try {
            // Intentamos crear el turno

            turnoJPA.createGenerico(turno);
            System.out.println("Turno creado exitosamente.");

        } catch (Exception e) {
            // Si ocurre un error al crear el turno, lanzamos una excepción personalizada

            throw new TurnoExcepciones.TurnoCreadoException("No se ha podido crear el turno: " + e.getMessage());
        }
    }

    // Para mostrar todos los turnos

    public List<Turno> findAllTurnos() {
        return turnoJPA.findAllGenerico();
    }

    // Filtra los turnos por fecha, estado o el ID del ciudadano

    public List<Turno> filterTurnos(java.util.Date fecha, Turno.Estado estado, Long ciudadanoId) {
        List<Turno> turnos = turnoJPA.findAllGenerico();
        return turnos.stream()
                .filter(turno -> turno.getFecha().equals(fecha)
                        && turno.getEstado() == estado
                        && turno.getCiudadano().getId().equals(ciudadanoId))
                .collect(Collectors.toList());
    }


    // Filtra los turnos por el ID del ciudadano

    public List<Turno> filterTurnosByCiudadanoId(Long ciudadanoId) {
        List<Turno> turnos = turnoJPA.findAllGenerico();
        return turnos.stream()
                .filter(turno -> turno.getCiudadano().getId().equals(ciudadanoId))
                .collect(Collectors.toList());
    }

    // Filtra los turnos por la fecha

    public List<Turno> filterTurnosByFecha(java.util.Date fecha) {
        List<Turno> turnos = turnoJPA.findAllGenerico();
        return turnos.stream()
                .filter(turno -> turno.getFecha().equals(fecha))
                .collect(Collectors.toList());
    }

    // Filtra los turnos por su estado

    public List<Turno> filterTurnosByEstado(Turno.Estado estado) {
        List<Turno> turnos = turnoJPA.findAllGenerico();
        return turnos.stream()
                .filter(turno -> turno.getEstado() == estado)
                .collect(Collectors.toList());
    }

    // Filtra los turnos por fecha, estado y el ID del ciudadano (filtros combinados, para una búsqueda más específica)

    public List<Turno> filterTurnosByFechaAndEstadoAndCiudadanoID(java.util.Date fecha, Turno.Estado estado, Long ciudadanoId) {
        List<Turno> turnos = turnoJPA.findAllGenerico();
        return turnos.stream()
                .filter(turno -> turno.getFecha().equals(fecha)
                        && turno.getEstado() == estado
                        && turno.getCiudadano().getId().equals(ciudadanoId))
                .collect(Collectors.toList());
    }

    // Filtra los turnos por fecha y el ID del ciudadano (filtros combinados, para una búsqueda más específica)

    public List<Turno> filterTurnosByFechaAndCiudadanoID(java.util.Date fecha, Long ciudadanoId) {
        List<Turno> turnos = turnoJPA.findAllGenerico();
        return turnos.stream()
                .filter(turno -> turno.getFecha().equals(fecha)
                        && turno.getCiudadano().getId().equals(ciudadanoId))
                .collect(Collectors.toList());
    }

    // Filtra los turnos por estado y el ID del ciudadano (filtros combinados, para una búsqueda más específica)

    public List<Turno> filterTurnosByEstadoAndCiudadanoID(Turno.Estado estado, Long ciudadanoId) {
        List<Turno> turnos = turnoJPA.findAllGenerico();
        return turnos.stream()
                .filter(turno -> turno.getEstado() == estado
                        && turno.getCiudadano().getId().equals(ciudadanoId))
                .collect(Collectors.toList());
    }

    // Filtra los turnos por fecha y estado (filtros combinados, para una búsqueda más específica)

    public List<Turno> filterTurnosByFechaAndEstado(java.util.Date fecha, Turno.Estado estado) {
        List<Turno> turnos = turnoJPA.findAllGenerico();
        return turnos.stream()
                .filter(turno -> turno.getFecha().equals(fecha) && turno.getEstado() == estado)
                .collect(Collectors.toList());
    }


    // Verifica si ya existe un turno con el mismo número

    public boolean turnoYaExiste(Integer numero) throws TurnoExcepciones.TurnoDuplicadoException {
        List<Turno> turnos = turnoJPA.findAllGenerico();
        for (Turno turno : turnos) {
            if (turno.getNumero().equals(numero)) {

                // Si el número ya existe, lanzamos una excepción personalizada

                throw new TurnoExcepciones.TurnoDuplicadoException("Ya existe un turno con el número " + numero);
            }
        }
        return false;  // Indicamos que no existe un turno con el mismo número
    }

    // Para actualizar los datos de un turno

    public void updateTurno(Turno turno) throws TurnoExcepciones.TurnoNoExistenteException, TurnoExcepciones.TurnoActualizadoException {

        // Primero verificamos si el turno ya existe en la base de datos

        Optional<Turno> turnoExistente = turnoJPA.findOneGenerico(turno.getId());
        if (turnoExistente.isPresent()) {
            try {
                // Intentamos actualizar el turno

                turnoJPA.updateGenerico(turno);
                System.out.println("Turno actualizado exitosamente.");

            } catch (Exception e) {
                // Si ocurre un error al actualizarlo, lanzamos una excepción personalizada

                throw new TurnoExcepciones.TurnoActualizadoException("No se ha podido actualizar el turno: " + e.getMessage());
            }
        } else {
            // Si el turno no existe, lanzamos una excepción personalizada

            throw new TurnoExcepciones.TurnoNoExistenteException("El turno con el ID " + turno.getId() + " no existe.");
        }
    }


    // Para eliminar un turno

    public void deleteTurno(Long id) throws TurnoExcepciones.TurnoNoExistenteException, TurnoExcepciones.TurnoEliminadoException {

        // Primero buscamos el turno en la base de datos

        Optional<Turno> turno = turnoJPA.findOneGenerico(id);
        if (turno.isPresent()) {
            try {
                // Intentamos eliminar el turno

                turnoJPA.deleteGenerico(id);
                System.out.println("Turno eliminado exitosamente.");

            } catch (Exception e) {
                // Si ocurre un error al eliminarlo, lanzamos una excepción personalizada

                throw new TurnoExcepciones.TurnoEliminadoException("No se ha podido eliminar el turno: " + e.getMessage());
            }
        } else {
            // Si el turno no existe, lanzamos una excepción personalizada

            throw new TurnoExcepciones.TurnoNoExistenteException("El turno con el ID " + id + " no existe.");
        }
    }

}