package com.rggt.edutecno.dao;

import com.rggt.edutecno.procesaconexion.DataConect;
import com.rggt.edutecno.modelo.Horoscopo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoroscopoDAOImp implements IHoroscopoDAO {

    private Connection conectar() throws SQLException, ClassNotFoundException {
        return DataConect.getInstance();
    }

    @Override //Obtiene los horoscopos de la base de datos
    public List<Horoscopo> obtenerHoroscopo() {
        List<Horoscopo> listaHoroscopo = new ArrayList<>();
        String sql = "SELECT * FROM HOROSCOPO;"; // Consulta para obtener todos los signos del horóscopo

        try (Statement st = conectar().createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                // Crear un objeto Horoscopo para cada registro
                Horoscopo horoscopo = new Horoscopo(
                        rs.getString("ANIMAL"),
                        rs.getDate("FECHA_INICIO"),
                        rs.getDate("FECHA_FIN")
                );
                listaHoroscopo.add(horoscopo); // Agregar el objeto a la lista
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Manejo de excepciones
        }

        return listaHoroscopo; // Retornar la lista de horóscopos
    }

    @Override //Recibe una fecha de nacimiento y llama a obtenerHoroscopo() para obtener todos los horóscopos.
    public String obtenerHoroscopoPorFecha(Date fechaNacimiento) {
        List<Horoscopo> listaHoroscopo = obtenerHoroscopo(); // Obtener todos los horóscopos
        java.sql.Date fechaSQL = new java.sql.Date(fechaNacimiento.getTime()); // Convierte fechaNacimiento a java.sql.Date para facilitar la comparación

        for (Horoscopo horoscopo : listaHoroscopo) { //Itera y verifica si la fecha de nacimiento esta dentro del rango de inicio-fin
            if (fechaSQL.after(horoscopo.getFechaInicio()) && fechaSQL.before(horoscopo.getFechaFin())) {
                return horoscopo.getAnimal(); // Retornar el animal correspondiente
            }
        }
        return null; // Retornar null si no se encuentra un horóscopo
    }
}
