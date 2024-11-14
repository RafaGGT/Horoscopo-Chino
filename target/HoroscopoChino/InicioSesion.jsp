<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <meta charset="UTF-8">
    <title>Iniciar Sesión</title>
</head>
<body>
<nav class="navbar bg-primary border-bottom border-body" data-bs-theme="dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Horóscopo Chino - Tu Horóscopo Chino</a>
    </div>
</nav>
<div class="container mt-5">
    <h1>Inicia sesión</h1>
    <p>Es necesario que inicies sesión para revisar tu horóscopo chino. Si todavía no tienes una cuenta, haz clic <a href="/HoroscopoChino/Registro.jsp">aquí</a>.</p>

    <%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
    %>
        <div class="alert alert-danger" role="alert">
            <%= errorMessage %>
        </div>
    <%
    }
    %>

    <form method="post" action="InicioSesion">
        <div class="row mb-3">
            <label for="usuario" class="col-sm-2 col-form-label">Usuario</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="usuario" name="usuario">
            </div>
        </div>
        <div class="row mb-3">
            <label for="clave" class="col-sm-2 col-form-label">Clave</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" id="clave" name="clave">
            </div>
        </div>
        <button type="submit" class="btn btn-primary">Iniciar Sesión</button>
    </form>
</div>
</body>
</html>
