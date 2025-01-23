<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.List, com.example.entities.Turno, com.example.entities.Turno.Estado" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agregar Turno</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<%@ include file="partials/header.jsp" %>

<div class="container mt-4">
    <h2>Agregar Nuevo Turno</h2>

    <!-- Mostramos errores si los hay -->

    <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-danger">
            <%= request.getAttribute("error") %>
        </div>
    <% } %>

    <!-- Mostramos mensajes de éxito si los hay -->

    <% if (request.getAttribute("success") != null) { %>
        <div class="alert alert-success">
            <%= request.getAttribute("success") %>
        </div>
    <% } %>

    <!-- Formulario para agregar un nuevo turno -->

    <form action="turnosForm" method="post" class="mb-4">
        <div class="form-group">
            <label for="numero">Número de Turno</label>
            <input type="number" id="numero" name="numero" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="descripcion">Descripción del Trámite</label>
            <input type="text" id="descripcion" name="descripcion" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="ciudadanoId">ID del Ciudadano</label>
            <input type="number" id="ciudadanoId" name="ciudadanoId" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="estado">Estado del Turno</label>
            <select id="estado" name="estado" class="form-control" required>
                <option value="EN_ESPERA">En espera</option>
                <option value="YA_ATENDIDO">Ya atendido</option>
            </select>
        </div>
        <div class="form-group">
            <label for="fecha">Fecha del Turno</label>
            <input type="date" id="fecha" name="fecha" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Agregar Turno</button>
    </form>
</div>
</body>
</html>
