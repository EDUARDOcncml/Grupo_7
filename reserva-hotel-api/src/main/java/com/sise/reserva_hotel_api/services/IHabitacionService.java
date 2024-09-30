package com.sise.reserva_hotel_api.services;

import com.sise.reserva_hotel_api.entities.Habitacion;
import java.util.List;


public interface IHabitacionService {
    List<Habitacion>listarHabitaciones() throws Exception;
    Habitacion obtenerHabitacion(Integer idHabitacion) throws Exception;
    Habitacion insertarHabitacion(Habitacion habitacion) throws Exception;
    Habitacion actualizarHabitacion(Habitacion habitacion) throws Exception;
    void darBajaHabitacion(Integer idHabitacion) throws Exception;
}
