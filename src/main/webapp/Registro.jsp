<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
     <title>Registro</title>
</head>
<body>
<script>
    <%
        String successMessage = (String) request.getAttribute("successMessage");
        String errorMessage = (String) request.getAttribute("errorMessage");
    %>
    <% if (successMessage != null) { %>
        alert("<%= successMessage %>");
        session.removeAttribute("successMessage");
    <% } else if (errorMessage != null) { %>
        alert("<%= errorMessage %>");
    <% } %>
</script>
<nav class="navbar bg-primary border-bottom border-body" data-bs-theme="dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Horóscopo Chino - Tu Horóscopo Chino</a>
    </div>
</nav>
<div class="container mt-5">
    <h2>Registro de Usuario</h2>
    <p>Ya estas registrado? haz click <a href="/HoroscopoChino/InicioSesion.jsp">aqui</a></p>
    <form action="registrarUsuario" method="post">
        <div class="row mb-3">
            <label for="nombre" class="col-sm-2 col-form-label">Nombre</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingrese su nombre" required>
            </div>
        </div>

        <div class="row mb-3">
            <label for="usuario" class="col-sm-2 col-form-label">Nombre de Usuario</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="username" name="username" placeholder="Ingrese su nombre de usuario" required>
            </div>
        </div>

       <div class="row mb-3">
           <label for="email" class="col-sm-2 col-form-label">Correo Electrónico</label>
           <div class="col-sm-4">
               <input type="email" class="form-control" id="email" name="email"
                      placeholder="Ingrese su correo electrónico" required
                      pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$">
           </div>
       </div>

        <div class="row mb-3">
            <label for="fechaNacimiento" class="col-sm-2 col-form-label">Fecha de Nacimiento</label>
            <div class="col-sm-4">
                <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" required>
            </div>
        </div>

        <div class="row mb-3">
            <label for="password" class="col-sm-2 col-form-label">Contraseña</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" id="password" name="password" placeholder="Ingrese su contraseña" required>
            </div>
        </div>

        <div class="row mb-3">
            <label for="confirmPassword" class="col-sm-2 col-form-label">Repetir Contraseña</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Repita su contraseña" required>
            </div>
        </div>

        <button type="submit" class="btn btn-primary">Registrar</button>
    </form>
</div>
</body>
</html>
