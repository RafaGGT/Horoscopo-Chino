<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String usuario = (String) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect(request.getContextPath() + "/InicioSesion.jsp");
        return;
    }
    String successMessage = (String) request.getAttribute("successMessage");
    String errorMessage = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  <meta charset="UTF-8">
  <title>Menu</title>
</head>
<body>
<script>
    <% if (successMessage != null) { %>
        alert("<%= successMessage %>");
    <% } else if (errorMessage != null) { %>
        alert("<%= errorMessage %>");
    <% } %>
</script>
<nav class="navbar bg-primary border-bottom border-body" data-bs-theme="dark">
    <div class="container-fluid d-flex justify-content-between align-items-center">
        <a class="navbar-brand" href="#">Horóscopo Chino - Tu Horóscopo Chino</a>

        <form action="CerrarSesion" method="post" class="d-inline">
            <button type="submit" class="btn btn-danger">Cerrar sesión</button>
        </form>
    </div>
</nav>
<div class="container mt-5">
  <h1>¿Qué deseas hacer, <%= usuario %>?</h1>
  <div class="container mt-5">
    <div class="d-flex justify-content-center">
      <button type="button" class="btn btn-primary mx-2" onclick="location.href='ConsultarHoroscopo'">Conoce tu animal</button>
      <button type="button" class="btn btn-primary mx-2" onclick="location.href='BuscarUsuario'">Buscar Usuarios</button>
      <button type="button" class="btn btn-primary mx-2" onclick="location.href='ModificarUsuario'">Modificar Datos</button>

      <form action="eliminarUsuario" method="post" onsubmit="return confirm('¿Estás seguro de que deseas eliminar tu cuenta?');">
          <button type="submit" class="btn btn-danger mx-2">Eliminar cuenta</button>
      </form>
    </div>
  </div>
</div>
</body>
</html>
