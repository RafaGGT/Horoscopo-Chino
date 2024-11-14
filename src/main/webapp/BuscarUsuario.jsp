<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.rggt.edutecno.modelo.Usuario" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Buscar Usuario</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
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
    <h2>A quien deseas buscar, <%= session.getAttribute("usuario") %>?</h2>

    <!-- Formulario de búsqueda -->
    <form action="BuscarUsuario" method="get" class="input-group mb-3">
        <input type="text" class="form-control" name="username" placeholder="Ingresa el username del usuario" required>
        <button class="btn btn-primary" type="submit">Buscar</button>
    </form>

    <!-- Tabla de resultados -->
    <div class="mt-4">
        <h3>Resultados de la búsqueda</h3>
        <table class="table table-bordered">
            <thead class="table-primary">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Username</th>
                    <th>Animal</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
                    if (usuarios != null && !usuarios.isEmpty()) {
                        for (Usuario usuario : usuarios) {
                %>
                            <tr>
                                <td><%= usuario.getId() %></td>
                                <td><%= usuario.getNombre() %></td>
                                <td><%= usuario.getUsername() %></td>
                                <td><%= usuario.getAnimal() %></td>
                            </tr>
                <%
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="4" class="text-center">No se encontraron usuarios</td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
     <div class="mt-3">
            <a href="Lobby.jsp" class="btn btn-primary">Volver al Lobby</a>
        </div>
</div>
</body>
</html>
