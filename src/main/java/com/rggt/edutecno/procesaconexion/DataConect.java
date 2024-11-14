package com.rggt.edutecno.procesaconexion;

import java.sql.Connection; //Conexion
import java.sql.DriverManager; //Gestiona las conexiones
import java.sql.SQLException; //Maneja las excepciones relacionadas

public class DataConect {
    //Instancia de Connection
    private static Connection c;
    private static final String url = "jdbc:mysql://localhost:3306/horoscop_chino?serverTimezone=America/Santiago";
    private static final String usuario = "root";
    private static final String clave = "raf123";

    public static Connection getInstance() throws SQLException, ClassNotFoundException {
        if (c == null) { //Verifica si la conexion "c" si es null para crearla
            Class.forName("com.mysql.jdbc.Driver"); // Carga el driver JDBC
            c = DriverManager.getConnection(url, usuario, clave); // Genera la conexión a la base con la url, usuario y clave
        }
        return c; // Devuelve la conexión ya sea creada recientemente o la creada previamente
    }
}
