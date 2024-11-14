package com.rggt.edutecno.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import jakarta.servlet.http.HttpSession;
import com.rggt.edutecno.dao.UsuarioDAO;
import com.rggt.edutecno.modelo.Usuario;

// Define el servlet y las URL que manejará
@WebServlet({"/InicioSesion"})
public class IniciaSesionServlet extends HttpServlet {

    // Instancia del DAO para manejar usuarios
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    // Método que maneja las peticiones POST para el inicio de sesión
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtiene los parámetros de usuario y clave del formulario
        String u = req.getParameter("usuario");
        String c = req.getParameter("clave");

        // Llama al método iniciarSesion del DAO para autenticar al usuario
        Optional<Usuario> optionalUsuario = usuarioDAO.iniciarSesion(u, c);

        // Verifica si se encontró un usuario con las credenciales proporcionadas
        if (optionalUsuario.isPresent()) {
            HttpSession session = req.getSession();
            // Almacena el nombre de usuario en la sesión
            session.setAttribute("usuario", optionalUsuario.get().getUsername());
            resp.sendRedirect(req.getContextPath() + "/Lobby.jsp");
        } else { // Si no se encontró el usuario
            req.setAttribute("errorMessage", "Usuario o contraseña incorrectos.");
            req.getRequestDispatcher("/InicioSesion.jsp").forward(req, resp);
        }
    }
}

