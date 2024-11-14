package com.rggt.edutecno.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/CerrarSesion")
public class CerrarSesionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener la sesión actual
        HttpSession session = req.getSession(false);
        if (session != null) {
            // Invalidar la sesión
            session.invalidate();
        }
        // Redirigir a la página de inicio de sesión
        resp.sendRedirect(req.getContextPath() + "/InicioSesion.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Manejo de la petición GET para cerrar sesión
        doPost(req, resp);
    }
}
