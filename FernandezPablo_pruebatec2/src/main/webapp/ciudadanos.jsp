<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.List, com.example.entities.Ciudadano" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Ciudadanos</title>

    <!-- Bootstrap CSS -->

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

</head>
<body>
<%@ include file="partials/header.jsp" %>

<div class="container mt-4">
    <h2 class="mb-4 text-center">Lista de Ciudadanos</h2>

    <!-- Mensaje confirmando el éxito de la operación -->

    <% String successMessage = (String) request.getAttribute("success"); %>
    <% if (successMessage != null) { %>
        <div class="alert alert-success">
            <%= successMessage %>
        </div>
    <% } %>

    <!-- Mensaje de error -->

    <% String errorMessage = (String) request.getAttribute("error"); %>
    <% if (errorMessage != null) { %>
        <div class="alert alert-danger">
            <%= errorMessage %>
        </div>
    <% } %>

    <!-- Botón para agregar un nuevo ciudadano -->

    <div class="text-right mb-4">
        <a href="ciudadanosForm" class="btn btn-primary">Agregar Nuevo Ciudadano</a>
    </div>

    <!-- Tabla con los ciudadanos -->

    <div class="table-responsive">
        <table class="table table-striped table-hover">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Apellidos</th>
                    <th>Documento de Identidad</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // Recuperamos la lista de ciudadanos desde el request
                    List<Ciudadano> ciudadanos = (List<Ciudadano>) request.getAttribute("ciudadanos");
                    if (ciudadanos != null && !ciudadanos.isEmpty()) {
                        for (Ciudadano ciudadano : ciudadanos) {
                %>
                <tr>
                    <td><%= ciudadano.getId() %></td>
                    <td><%= ciudadano.getNombre() %></td>
                    <td><%= ciudadano.getApellido() %></td>
                    <td><%= ciudadano.getDni() %></td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="4" class="text-center">No hay ciudadanos registrados.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</div>

<!-- Scripts de Bootstrap -->

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.4.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
