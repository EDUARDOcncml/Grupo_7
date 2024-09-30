package com.sise.reserva_hotel_api.services;

import com.sise.reserva_hotel_api.entities.Habitacion;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface IHabitacionService {
    List<Habitacion>listarHabitaciones();
    Habitacion obtenerHabitacion(Integer idHabitacion);
    Habitacion insertarHabitacion(Habitacion habitacion);
    Habitacion actualizarHabitacion(Habitacion habitacion);
    void darBajaHabitacion(Integer idHabitacion);


    List<Habitacion> listarHabitacionOrdenadas(Sort sort);
    Page<Habitacion> listarHabitacionPaginadas(Pageable pageable);
}
