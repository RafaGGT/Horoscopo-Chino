<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tu Horóscopo Chino</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar bg-primary border-bottom border-body" data-bs-theme="dark">
    <div class="container-fluid d-flex justify-content-between align-items-center">
        <a class="navbar-brand" href="#">Horóscopo Chino - Tu Horóscopo Chino</a>

        <form action="CerrarSesion" method="post" class="d-inline">
            <button type="submit" class="btn btn-danger">Cerrar sesión</button>
        </form>
    </div>
</nav>
<div class="container mt-5">
    <h1>Tu signo del horóscopo chino es: <%= request.getAttribute("horoscopo") != null ? request.getAttribute("horoscopo") : "No disponible" %></h1>
    <div class="mt-3">
        <a href="Lobby.jsp" class="btn btn-primary">Volver al Lobby</a>
    </div>
</div>
</body>
</html>
