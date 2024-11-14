package com.rggt.edutecno.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.rggt.edutecno.dao.HoroscopoDAOImp; // Asegúrate de importar la clase correctamente
import com.rggt.edutecno.dao.UsuarioDAO;
import com.rggt.edutecno.modelo.Usuario;

// Anotación que indica la URL a la que este servlet responde
@WebServlet("/ModificarUsuario")
public class ModificarUsuarioServlet extends HttpServlet {

    // Instancia del DAO para acceder a la base de datos de usuarios
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final HoroscopoDAOImp horoscopoDAO = new HoroscopoDAOImp(); // Crear una instancia de HoroscopoDAOImp

    // Método para obtener el nuevo animal zodiacal basado en la fecha de nacimiento
    private String obtenerNuevoAnimal(Date fechaNacimiento) {
        return horoscopoDAO.obtenerHoroscopoPorFecha(fechaNacimiento); // Usar la instancia existente
    }

    // Método que maneja las solicitudes POST
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(); // Obtener la sesión actual
        String username = (String) session.getAttribute("usuario"); // Obtener el nombre de usuario de la sesión

        // Verificar si el usuario está autenticado
        if (username != null) {
            // Obtener el usuario correspondiente al nombre de usuario de la sesión
            Usuario usuario = usuarioDAO.obtenerPorUsername(username).orElse(null);

            // Si el usuario existe
            if (usuario != null) {
                // Obtener los nuevos datos del formulario
                String nuevoNombre = req.getParameter("nombre");
                String nuevoUsername = req.getParameter("username");
                String nuevoEmail = req.getParameter("email");
                String nuevaFechaNacimiento = req.getParameter("fechaNacimiento");
                String nuevaClave = req.getParameter("password");

                // Actualizar el nombre si se proporciona un nuevo valor
                if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
                    usuario.setNombre(nuevoNombre);
                }

                // Actualizar el nombre de usuario si se proporciona un nuevo valor
                if (nuevoUsername != null && !nuevoUsername.isEmpty()) {
                    usuario.setUsername(nuevoUsername);
                }

                // Actualizar el email si se proporciona un nuevo valor
                if (nuevoEmail != null && !nuevoEmail.isEmpty()) {
                    usuario.setEmail(nuevoEmail);
                }

                // Actualizar la fecha de nacimiento si se proporciona un nuevo valor
                if (nuevaFechaNacimiento != null && !nuevaFechaNacimiento.isEmpty()) {
                    try {
                        // Convertir la fecha de nacimiento en formato String a Date
                        Date fechaNacimientoUtil = new SimpleDateFormat("yyyy-MM-dd").parse(nuevaFechaNacimiento);
                        usuario.setFechaNacimiento(fechaNacimientoUtil);

                        // Llama al método para obtener el nuevo animal basado en la nueva fecha
                        String nuevoAnimal = obtenerNuevoAnimal(fechaNacimientoUtil);
                        usuario.setAnimal(nuevoAnimal);
                    } catch (ParseException e) {
                        e.printStackTrace(); // Imprimir el error en la consola
                        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Fecha de nacimiento no válida."); // Enviar error si la fecha es inválida
                        return; // Terminar la ejecución del método
                    }
                }

                // Actualizar la contraseña si se proporciona un nuevo valor
                if (nuevaClave != null && !nuevaClave.isEmpty()) {
                    usuario.setPassword(nuevaClave);
                }

                // Actualizar los datos del usuario en la base de datos
                usuarioDAO.actualizar(usuario);

                // Actualizar la sesión con el nuevo username si ha cambiado
                if (!username.equals(nuevoUsername)) {
                    session.setAttribute("usuario", nuevoUsername);
                }

                // Redirigir al usuario a la página del Lobby después de la modificación exitosa
                resp.sendRedirect(req.getContextPath() + "/Lobby.jsp");
            } else {
                // Enviar error si no se encuentra el usuario
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Usuario no encontrado.");
            }
        } else {
            // Redirigir a la página de inicio de sesión si el usuario no está autenticado
            resp.sendRedirect(req.getContextPath() + "/InicioSesion.jsp");
        }
    }

    // Método que maneja las solicitudes GET
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(); // Obtener la sesión actual
        String username = (String) session.getAttribute("usuario"); // Obtener el nombre de usuario de la sesión

        // Verificar si el usuario está autenticado
        if (username != null) {
            Usuario usuario = usuarioDAO.obtenerPorUsername(username).orElse(null);

            // Si el usuario existe
            if (usuario != null) {
                req.setAttribute("usuario", usuario); // Agregar el usuario a la solicitud para enviarlo al JSP
                // Redirigir al JSP que permite modificar los datos del usuario
                req.getRequestDispatcher("/ModificarUsuario.jsp").forward(req, resp);
            } else {
                // Enviar error si no se encuentra el usuario
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Usuario no encontrado.");
            }
        } else {
            // Redirigir a la página de inicio de sesión si el usuario no está autenticado
            resp.sendRedirect(req.getContextPath() + "/InicioSesion.jsp");
        }
    }
}
