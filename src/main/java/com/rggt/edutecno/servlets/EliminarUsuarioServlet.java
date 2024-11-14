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
import java.util.Optional;

@WebServlet("/eliminarUsuario") // Define la URL del servlet
public class EliminarUsuarioServlet extends HttpServlet {

    // Instancia del DAO para interactuar con la base de datos de usuarios
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener el nombre de usuario de la sesión
        String username = (String) req.getSession().getAttribute("usuario");

        // Obtener el usuario por nombre de usuario usando el DAO
        Optional<Usuario> usuarioOptional = usuarioDAO.obtenerPorUsername(username);

        // Verifica si el usuario existe
        if (usuarioOptional.isPresent()) {
            // Intenta eliminar el usuario por nombre de usuario
            boolean eliminado = usuarioDAO.eliminarPorUsername(username);

            if (eliminado) {
                // Si la cuenta se eliminó exitosamente
                HttpSession session = req.getSession();
                session.invalidate(); // Invalida la sesión para cerrar la sesión del usuario

                // Redirigir con un mensaje de éxito utilizando un parámetro de consulta
                resp.sendRedirect(req.getContextPath() + "/index.jsp?successMessage=Cuenta eliminada exitosamente.");
            } else {
                // Si hubo un error al eliminar la cuenta
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al eliminar la cuenta.");
            }
        } else {
            // Si no se encontró el usuario
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "No se encontró el usuario.");
        }
    }

}
