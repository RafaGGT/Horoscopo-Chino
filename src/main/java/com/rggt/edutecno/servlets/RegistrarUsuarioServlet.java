package com.rggt.edutecno.servlets;

import com.rggt.edutecno.modelo.Usuario;
import com.rggt.edutecno.dao.HoroscopoDAOImp;
import com.rggt.edutecno.dao.UsuarioDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet("/registrarUsuario")
public class RegistrarUsuarioServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String fechaNacimientoStr = request.getParameter("fechaNacimiento");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Verificar que ambas contraseñas sean iguales
        if (!password.equals(confirmPassword)) {
            // Redirigir a la página de registro con un mensaje de error
            request.setAttribute("errorMessage", "No son las mismas contraseñas.");
            request.getRequestDispatcher("/Registro.jsp").forward(request, response);
            return;
        }
        // Parsear la fecha de nacimiento
        Date fechaNacimiento;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fechaNacimiento = dateFormat.parse(fechaNacimientoStr);
        } catch (ParseException e) {
            response.getWriter().write("Error en el formato de la fecha de nacimiento.");
            return;
        }

        // Crear un nuevo usuario
        Usuario usuario = new Usuario(0, nombre, username, email, fechaNacimiento, password, null);

        // Obtener el horóscopo usando el método optimizado en HoroscopoDAOImp
        HoroscopoDAOImp horoscopoDAO = new HoroscopoDAOImp();
        String animal = horoscopoDAO.obtenerHoroscopoPorFecha(fechaNacimiento);

        if (animal != null) {
            usuario.setAnimal(animal);
        }

        // Registrar el usuario en la base de datos
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean registrado = usuarioDAO.agregar(usuario);

        // Manejo de la respuesta
        if (registrado) {
            request.setAttribute("successMessage", "Registro Correcto.");
        } else {
            request.setAttribute("errorMessage", "Error al registrar.");
            return;
        }

        request.getRequestDispatcher("/Registro.jsp").forward(request, response);
    }
}
