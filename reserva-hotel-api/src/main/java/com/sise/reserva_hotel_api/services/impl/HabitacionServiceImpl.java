package com.sise.reserva_hotel_api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sise.reserva_hotel_api.entities.Habitacion;
import com.sise.reserva_hotel_api.repositories.IHabitacionRepository;
import com.sise.reserva_hotel_api.services.IHabitacionService;


@Service
public class HabitacionServiceImpl implements IHabitacionService {

    @Autowired
    private IHabitacionRepository habitacionRepository;

    @Override
    public List<Habitacion> listarHabitaciones() throws Exception {
        return habitacionRepository.findByEstadoAuditoria("1");
    }

    @Override
    public Habitacion obtenerHabitacion(Integer idHabitacion) throws Exception {
        return habitacionRepository.findOneByIdHabitacionAndEstadoAuditoria(idHabitacion, "1");
    }

    @Override
    public Habitacion insertarHabitacion(Habitacion habitacion) throws Exception {
        return habitacionRepository.save(habitacion);
    }

    @Override
    public Habitacion actualizarHabitacion(Habitacion habitacion) throws Exception {
        return habitacionRepository.save(habitacion);
    }

    @Override
    public void darBajaHabitacion(Integer idHabitacion) throws Exception {
        habitacionRepository.darBajaHabitacion(idHabitacion);
    }
    
}
