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

@WebServlet("/ConsultarHoroscopo")
public class ConsultarHoroscopoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtiene la sesión actual del usuario
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("usuario");

        // Verifica si el nombre de usuario no es nulo
        if (username != null) {
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.obtenerPorUsername(username).orElse(null);
            // Si existe el usuario, reenvía la solicitud al JSP HoroscopoC
            if (usuario != null) {
                req.setAttribute("horoscopo", usuario.getAnimal());
                getServletContext().getRequestDispatcher("/HoroscopoC.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Usuario no encontrado.");
            }
        } else { // Redirigir si no hay sesión
            resp.sendRedirect(req.getContextPath() + "/InicioSesion.jsp");
        }
    }
}
