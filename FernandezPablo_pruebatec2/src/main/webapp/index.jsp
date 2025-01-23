<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pagina de Bienvenida</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .welcome-container {
            text-align: center;
            padding: 30px;
            background: white;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            margin-top: 0;
        }
        .welcome-header {
            font-size: 2.5rem;
            font-weight: bold;
            color: #343a40;
        }
        .welcome-message {
            font-size: 1.2rem;
            color: #6c757d;
        }
        .btn-primary {
            margin-top: 20px;
            font-size: 1rem;
            padding: 10px 20px;
        }

        .container {
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
    </style>
</head>
<body>

<%@ include file="partials/header.jsp" %>

<div class="container">
    <div class="welcome-container">
        <h2 class="welcome-header">Bienvenido a la pagina principal.</h2>
        <p class="welcome-message">
            Nos complace tenerte aqui. Usa el menu en la parte superior para explorar las funcionalidades disponibles.
        </p>
        <p class="welcome-message">
            - Hecho por Pablo Fernandez Barredo
        </p>
    </div>
</div>

</body>
</html>
