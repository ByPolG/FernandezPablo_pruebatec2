package com.example.servlets;

import com.example.controllers.CiudadanoController;
import com.example.entities.Ciudadano;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/ciudadanos")
public class CiudadanoServlet extends HttpServlet {

    private final CiudadanoController ciudadanoController = new CiudadanoController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtenemos la lista completa de los ciudadanos y los enviamos a la página

        List<Ciudadano> ciudadanos = ciudadanoController.findAllCiudadanos();
        request.setAttribute("ciudadanos", ciudadanos);
        request.getRequestDispatcher("ciudadanos.jsp").forward(request, response);
    }
}
