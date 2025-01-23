package com.example.servlets;

import com.example.controllers.TurnoController;
import com.example.entities.Turno;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/turnos")
public class TurnoServlet extends HttpServlet {

    private final TurnoController turnoController = new TurnoController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fechaStr = request.getParameter("fecha");
        String estadoStr = request.getParameter("estado");
        String ciudadanoIdStr = request.getParameter("ciudadanoId");

        List<Turno> turnos;

        try {
            Date fecha = null;
            if (fechaStr != null && !fechaStr.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                fecha = sdf.parse(fechaStr);
            }

            Turno.Estado estado = null;
            if (estadoStr != null && !estadoStr.isEmpty()) {
                estado = Turno.Estado.fromString(estadoStr);
            }

            Long ciudadanoId = null;
            if (ciudadanoIdStr != null && !ciudadanoIdStr.isEmpty()) {
                ciudadanoId = Long.parseLong(ciudadanoIdStr);
            }

            // Filtramos los turnos según los parámetros de fecha, estado y ciudadanoId
            if (fecha != null && estado != null && ciudadanoId != null) {
                turnos = turnoController.filterTurnosByFechaAndEstadoAndCiudadanoID(fecha, estado, ciudadanoId);
            } else if (fecha != null && ciudadanoId != null) {
                turnos = turnoController.filterTurnosByFechaAndCiudadanoID(fecha, ciudadanoId);
            } else if (estado != null && ciudadanoId != null) {
                turnos = turnoController.filterTurnosByEstadoAndCiudadanoID(estado, ciudadanoId);
            } else if (fecha != null && estado != null) {
                turnos = turnoController.filterTurnosByFechaAndEstado(fecha, estado);
            } else if (fecha != null) {
                turnos = turnoController.filterTurnosByFecha(fecha);
            } else if (estado != null) {
                turnos = turnoController.filterTurnosByEstado(estado);
            } else if (ciudadanoId != null) {
                turnos = turnoController.filterTurnosByCiudadanoId(ciudadanoId);
            } else {
                turnos = turnoController.findAllTurnos();
            }

        } catch (Exception e) {

            // Si hay un error en el filtro, devolvemos todos los turnos como fallback

            turnos = turnoController.findAllTurnos();
        }

        // Nos aseguramos de que la lista no sea null antes de enviarla a la página

        if (turnos == null) {
            turnos = new ArrayList<>();  // Conesto evitamos que la lista sea null
        }

        request.setAttribute("turnos", turnos);
        request.getRequestDispatcher("turnos.jsp").forward(request, response);
    }
}
