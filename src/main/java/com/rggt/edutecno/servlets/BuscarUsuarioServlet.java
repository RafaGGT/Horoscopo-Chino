package com.rggt.edutecno.servlets;

import com.rggt.edutecno.dao.UsuarioDAO;
import com.rggt.edutecno.modelo.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/BuscarUsuario")
public class BuscarUsuarioServlet extends HttpServlet {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtiene la sesión actual del usuario
        HttpSession session = request.getSession();

        // Recupera el atributo "usuario" de la sesión
        String username = (String) session.getAttribute("usuario");

        // Verifica si el usuario está autenticado
        if (username == null) {
            // Si el usuario no está autenticado, redirige a la página de inicio de sesión
            response.sendRedirect(request.getContextPath() + "/InicioSesion.jsp");
            return; // Detiene la ejecución del servlet
        }

        // Si el usuario está autenticado, procede a buscar el usuario
        String usernameToSearch = request.getParameter("username");

        // Lista de usuarios filtrados por el username
        List<Usuario> usuariosFiltrados = usuarioDAO.listar().stream()
                .filter(usuario -> usuario.getUsername().equalsIgnoreCase(usernameToSearch))
                .collect(Collectors.toList());

        // Entrega la lista filtrada al JSP
        request.setAttribute("usuarios", usuariosFiltrados);
        request.getRequestDispatcher("/BuscarUsuario.jsp").forward(request, response);
    }
}
