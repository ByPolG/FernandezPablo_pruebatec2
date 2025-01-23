<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.List, com.example.entities.Turno, com.example.entities.Turno.Estado" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Turnos</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<%@ include file="partials/header.jsp" %>

<div class="container mt-4">
    <h2 class="mb-4 text-center">Lista de Turnos</h2>

    <!-- Mostramos errores si los hay -->

    <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-danger">
            <%= request.getAttribute("error") %>
        </div>
    <% } %>

    <!-- Filtro de búsqueda -->

    <form class="form-inline mb-4" method="get" action="turnos">
        <div class="form-group">
            <label for="fecha" class="mr-2">Fecha</label>
            <input type="date" name="fecha" id="fecha" class="form-control mr-3">
        </div>
        <div class="form-group">
            <label for="estado" class="mr-2">Estado</label>
            <select name="estado" id="estado" class="form-control mr-3">
                <option value="">Todos</option>
                <option value="EN_ESPERA">En espera</option>
                <option value="YA_ATENDIDO">Ya atendido</option>
            </select>
        </div>
        <div class="form-group">
            <label for="ciudadanoId" class="mr-2">ID del Ciudadano</label>
            <input type="number" name="ciudadanoId" id="ciudadanoId" class="form-control mr-3">
        </div>
        <button type="submit" class="btn btn-secondary">Filtrar</button>
    </form>

    <!-- Botón para agregar un nuevo turno -->

    <div class="text-right mb-4">
        <a href="turnosForm" class="btn btn-primary">Agregar Nuevo Turno</a>
    </div>

    <!-- Mostramos la lista de los turnos -->

    <div class="table-responsive">
        <table class="table table-striped table-hover">
            <thead class="thead-dark">
                <tr>
                    <th>Número</th>
                    <th>Descripción</th>
                    <th>Fecha</th>
                    <th>Estado</th>
                    <th>Ciudadano</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Turno> turnos = (List<Turno>) request.getAttribute("turnos");
                    if (turnos != null && !turnos.isEmpty()) {
                        for (Turno turno : turnos) {
                %>
                <tr>
                    <td><%= turno.getNumero() %></td>
                    <td><%= turno.getDescripcion() %></td>
                    <td><%= new java.text.SimpleDateFormat("dd/MM/yyyy").format(turno.getFecha()) %></td>
                    <td><%= turno.getEstado().getDescripcion() %></td>
                    <td><%= turno.getCiudadano().getNombre() + " " + turno.getCiudadano().getApellido() %></td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="5" class="text-center">No hay turnos disponibles</td>
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
