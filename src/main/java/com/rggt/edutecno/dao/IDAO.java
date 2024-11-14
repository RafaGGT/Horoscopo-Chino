package com.rggt.edutecno.dao;

import java.util.List;
import java.util.Optional;

public interface IDAO<T> {
        List<T> listar();                               // Listar todos los registros
        Optional<T> obtenerPorUsername(String username); // Obtener un registro por su username
        Boolean agregar(T t);                           // Agregar un nuevo registro
        Boolean eliminarPorUsername(String username);    // Eliminar un registro por su username
        Boolean actualizar(T t);                        // Actualizar un registro existente
        Optional<T> iniciarSesion(String username, String password); // Iniciar sesión
}
