<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Agregar Ciudadano</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<%@ include file="partials/header.jsp" %>

<div class="container mt-4">
    <h2 class="mb-4">Agregar Nuevo Ciudadano</h2>

    <!-- Mensaje confirmando el éxito de la operación -->

    <%
        String successMessage = (String) request.getAttribute("success");
        if (successMessage != null) {
    %>
        <div class="alert alert-success">
            <%= successMessage %>
        </div>
    <%
        }
    %>

    <!-- Mensaje de error -->

    <%
        String errorMessage = (String) request.getAttribute("error");
        if (errorMessage != null) {
    %>
        <div class="alert alert-danger">
            <%= errorMessage %>
        </div>
    <%
        }
    %>

    <!-- Formulario para agregar un ciudadano -->
    
    <form action="ciudadanosForm" method="post" class="mb-4">
        <div class="form-group">
            <label for="nombre">Nombre</label>
            <input type="text" id="nombre" name="nombre" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="apellido">Apellidos</label>
            <input type="text" id="apellido" name="apellido" class="form-control" required>
        </div>
        <div class="form-group">
            <label for="dni">Documento de Identidad</label>
            <input type="text" id="dni" name="dni" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Agregar Ciudadano</button>
    </form>
</div>
</body>
</html>
