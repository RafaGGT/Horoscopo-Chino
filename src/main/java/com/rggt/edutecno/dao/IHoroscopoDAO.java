package com.rggt.edutecno.dao;

import com.rggt.edutecno.modelo.Horoscopo;
import java.util.Date;
import java.util.List;

public interface IHoroscopoDAO {
    List<Horoscopo> obtenerHoroscopo();

    // Método para obtener el horóscopo por fecha de nacimiento
    String obtenerHoroscopoPorFecha(Date fechaNacimiento);
}
