package com.rggt.edutecno.dao;

import com.rggt.edutecno.modelo.Usuario;
import com.rggt.edutecno.procesaconexion.DataConect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioDAO implements IDAO<Usuario> {
    //crea y devuelve una conexión a la base de datos
    private Connection conectar() throws SQLException, ClassNotFoundException {
        return DataConect.getInstance();
    }

    // Metodos boleean que indican el fracaso o exito de la ejecucion de estos mismos

    // Busca un usuario por su username y devuelve un Optional<Usuario>
    @Override
    public Optional<Usuario> obtenerPorUsername(String username) {
        // Inicializa el optional pero vacio
        Optional<Usuario> optionalUsuario = Optional.empty();
        // Consulta sql
        String sql = "SELECT * FROM USUARIOS WHERE USERNAME = ?";
        // Prepara la conexion de la consulta
        try (PreparedStatement pst = conectar().prepareStatement(sql)) {
            pst.setString(1, username);
            // Hace la consulta y almacena los datos en ResultSet
            try (ResultSet rs = pst.executeQuery()) {
                // Hace un mapeo de los datos y los encapsula en el optional
                if (rs.next()) {
                    optionalUsuario = Optional.of(new Usuario(
                            rs.getInt("ID"),
                            rs.getString("NOMBRE"),
                            rs.getString("USERNAME"),
                            rs.getString("EMAIL"),
                            rs.getDate("FECHA_NACIMIENTO"),
                            rs.getString("PASSWORD"),
                            rs.getString("ANIMAL")
                    ));
                }
            } //Excepcion slq y clases  no encontradas
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } // Devuelve el optionalUsuario
        return optionalUsuario;
    }



    @Override //Devuelve una lista de todos los usuarios
    public List<Usuario> listar() {
        //Crea una lista vacía
        List<Usuario> usuarios = new ArrayList<>();
        try (Statement st = conectar().createStatement();
             // Ejecuta la consulta para obtener los usuarios
             ResultSet rs = st.executeQuery("SELECT * FROM USUARIOS;")) {
            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getInt("ID"),
                        rs.getString("NOMBRE"),
                        rs.getString("USERNAME"),
                        rs.getString("EMAIL"),
                        rs.getDate("FECHA_NACIMIENTO"),
                        rs.getString("PASSWORD"),
                        rs.getString("ANIMAL")
                );
                usuarios.add(u);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    @Override //Metodo para registrar usuarios
    public Boolean agregar(Usuario u) {
        // insertar un nuevo usuario en la base de datos
        String sql = "INSERT INTO USUARIOS (NOMBRE, USERNAME, EMAIL, FECHA_NACIMIENTO, PASSWORD, ANIMAL) VALUES ('"
                + u.getNombre() + "', '"
                + u.getUsername() + "', '"
                + u.getEmail() + "', '"
                + new java.sql.Date(u.getFechaNacimiento().getTime()) + "', '"
                + u.getPassword() + "', '"
                + u.getAnimal() + "');";
        try (Statement st = conectar().createStatement()) {
            st.execute(sql);
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override //Metodo para eliminar usuarios
    public Boolean eliminarPorUsername(String username) {
        String sql = "DELETE FROM USUARIOS WHERE USERNAME = ?;";
        try (PreparedStatement pst = conectar().prepareStatement(sql)) {
            pst.setString(1, username);
            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override //Metodo encargado de modificar datos
    public Boolean actualizar(Usuario u) {
        String act = "UPDATE USUARIOS SET "
                + "NOMBRE = '" + u.getNombre() + "', "
                + "USERNAME = '" + u.getUsername() + "', "
                + "EMAIL = '" + u.getEmail() + "', "
                + "FECHA_NACIMIENTO = '" + new java.sql.Date(u.getFechaNacimiento().getTime()) + "', "
                + "PASSWORD = '" + u.getPassword() + "', "
                + "ANIMAL = '" + u.getAnimal() + "' "
                + "WHERE ID = " + u.getId() + ";";
        try (Statement st = conectar().createStatement()) {
            st.executeUpdate(act);
            return true;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // Metodo de inicio sesion
    public Optional<Usuario> iniciarSesion(String username, String password) {
        String sql = "SELECT * FROM USUARIOS WHERE USERNAME = ? AND PASSWORD = ?";
        try (PreparedStatement pst = conectar().prepareStatement(sql)) {
            pst.setString(1, username);
            pst.setString(2, password);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Usuario(
                            rs.getInt("ID"),
                            rs.getString("NOMBRE"),
                            rs.getString("USERNAME"),
                            rs.getString("EMAIL"),
                            rs.getDate("FECHA_NACIMIENTO"),
                            rs.getString("PASSWORD"),
                            rs.getString("ANIMAL")
                    ));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
