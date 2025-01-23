package com.example.servlets;

import com.example.controllers.CiudadanoController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/ciudadanosForm")
public class CiudadanoFormServlet extends HttpServlet {

    private final CiudadanoController ciudadanoController = new CiudadanoController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirigige al formulario de creación de ciudadanos

        request.getRequestDispatcher("ciudadanosForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String dni = request.getParameter("dni");

        // Validamos los parámetros

        if (nombre == null || nombre.isEmpty() || apellido == null || apellido.isEmpty() || dni == null || dni.isEmpty()) {
            request.setAttribute("error", "Todos los campos son obligatorios.");
            request.getRequestDispatcher("ciudadanosForm.jsp").forward(request, response);
            return;
        }

        // Verificamos si el dni ya existe

        if (ciudadanoController.findAllCiudadanos().stream().anyMatch(c -> c.getDni().equals(dni))) {
            request.setAttribute("error", "Ya existe un ciudadano con el DNI " + dni);
            request.getRequestDispatcher("ciudadanosForm.jsp").forward(request, response);
            return;
        }

        // Crearmos el ciudadano

        try {
            ciudadanoController.createCiudadano(nombre, apellido, dni);
            request.setAttribute("success", "El ciudadano ha sido creado exitosamente.");
        } catch (Exception e) {
            request.setAttribute("error", "Error al crear ciudadano: " + e.getMessage());
        }

        // Nos mantenemos en la página mostraremos un mensaje de éxito o de error, dependiendo de la validez de los datos

        request.getRequestDispatcher("ciudadanosForm.jsp").forward(request, response);
    }
}
