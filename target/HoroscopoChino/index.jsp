<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Horoscopo Chino</title>
</head>
<body>
<%
    String successMessage = request.getParameter("successMessage");
    if (successMessage != null) {
%>
    <script>
        alert("<%= successMessage %>");
    </script>
<%
    }
%>
<nav class="navbar bg-primary border-bottom border-body" data-bs-theme="dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Horóscopo Chino - Tu Horóscopo Chino</a>
  </div>
</nav>
<div class="container d-flex flex-column align-items-center justify-content-center vh-100 bg-body-secondary">
    <h1 class="mb-4">Conoce tu horóscopo chino</h1>
    <button onclick="window.location.href='/HoroscopoChino/InicioSesion.jsp'" type="button" class="btn btn-primary btn-lg mb-3">Iniciar sesión</button>
    <button onclick="window.location.href='/HoroscopoChino/Registro.jsp'" type="button" class="btn btn-secondary btn-lg">Regístrate</button>
</div>
</body>
</html>



