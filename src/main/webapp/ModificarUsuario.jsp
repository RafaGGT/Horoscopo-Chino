<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.rggt.edutecno.modelo.Usuario" %> <!-- Asegúrate de importar la clase Usuario -->
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <meta charset="UTF-8">
    <title>Modificar Usuario</title>
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
    <%
        String username = (String) session.getAttribute("usuario");
        if (username == null) {
            response.sendRedirect(request.getContextPath() + "/InicioSesion.jsp");
            return;
        }
        Usuario usuario = (Usuario) request.getAttribute("usuario");
    %>
    <h1>Modificar Datos para <%= usuario != null ? usuario.getUsername() : "Usuario Desconocido" %></h1>
    <form action="ModificarUsuario" method="post">
        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre</label>
            <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingrese su nuevo nombre" value="<%= usuario != null ? usuario.getNombre() : "" %>">
        </div>
        <div class="mb-3">
            <label for="username" class="form-label">Nombre de Usuario</label>
            <input type="text" class="form-control" id="username" name="username" placeholder="Ingrese su nuevo nombre de usuario" value="<%= usuario != null ? usuario.getUsername() : "" %>">
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Correo Electrónico</label>
            <input type="email" class="form-control" id="email" name="email" placeholder="Ingrese su nuevo correo" value="<%= usuario != null ? usuario.getEmail() : "" %>" pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$">
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Contraseña Nueva</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Ingrese su nueva contraseña">
        </div>
        <div class="mb-3">
            <label for="fechaNacimiento" class="form-label">Fecha de Nacimiento</label>
            <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" value="<%= usuario != null ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(usuario.getFechaNacimiento()) : "" %>">
        </div>
        <button type="submit" class="btn btn-primary">Modificar</button>
    </form>
     <div class="mt-3">
            <a href="Lobby.jsp" class="btn btn-primary">Volver al Lobby</a>
        </div>
</div>
</body>
</html>
