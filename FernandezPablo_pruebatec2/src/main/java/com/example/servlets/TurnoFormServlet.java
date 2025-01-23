package com.example.servlets;

import com.example.controllers.CiudadanoController;
import com.example.controllers.TurnoController;
import com.example.entities.Ciudadano;
import com.example.entities.Turno;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Optional;

@WebServlet(urlPatterns = "/turnosForm")
public class TurnoFormServlet extends HttpServlet {

    private final TurnoController turnoController = new TurnoController();
    private final CiudadanoController ciudadanoController = new CiudadanoController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("turnosForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Capturamos todos los parámetros

            String numeroStr = request.getParameter("numero");
            String descripcion = request.getParameter("descripcion");
            String ciudadanoIdStr = request.getParameter("ciudadanoId");
            String estadoStr = request.getParameter("estado");
            String fechaStr = request.getParameter("fecha");

            // Nos aseguramos de que ningún campo quede vacío

            if (numeroStr == null || numeroStr.isEmpty() ||
                    descripcion == null || descripcion.isEmpty() ||
                    ciudadanoIdStr == null || ciudadanoIdStr.isEmpty() ||
                    estadoStr == null || estadoStr.isEmpty() ||
                    fechaStr == null || fechaStr.isEmpty()) {
                request.setAttribute("error", "Todos los campos son obligatorios.");
                request.getRequestDispatcher("turnosForm.jsp").forward(request, response);
                return;
            }

            // Convertimos los valores

            Integer numero = Integer.parseInt(numeroStr);
            Long ciudadanoId = Long.parseLong(ciudadanoIdStr);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fecha = sdf.parse(fechaStr);
            Turno.Estado estado = Turno.Estado.fromString(estadoStr);

            // Verificamos si el turno ya existe

            if (turnoController.turnoYaExiste(numero)) {
                request.setAttribute("error", "Ya existe un turno con el número " + numero);
                request.getRequestDispatcher("turnosForm.jsp").forward(request, response);
                return;
            }

            // Verificamos la existencia del ciudadano

            Optional<Ciudadano> ciudadanoOpt = ciudadanoController.findCiudadanoById(ciudadanoId);
            if (!ciudadanoOpt.isPresent()) {
                request.setAttribute("error", "Ciudadano no encontrado.");
                request.getRequestDispatcher("turnosForm.jsp").forward(request, response);
                return;
            }

            // Creamos el turno

            Ciudadano ciudadano = ciudadanoOpt.get();
            turnoController.createTurno(numero, descripcion, ciudadano, estado, fecha);

            // Enviamos un mensaje, confirmando el éxito de la operación

            request.setAttribute("success", "El turno ha sido creado exitosamente.");
            request.getRequestDispatcher("turnosForm.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Formato de número o ID inválido.");
            request.getRequestDispatcher("turnosForm.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error procesando la solicitud: " + e.getMessage());
            request.getRequestDispatcher("turnosForm.jsp").forward(request, response);
        }
    }
}
