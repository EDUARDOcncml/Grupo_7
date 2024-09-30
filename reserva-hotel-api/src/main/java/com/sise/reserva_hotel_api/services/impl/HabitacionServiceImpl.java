package com.sise.reserva_hotel_api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sise.reserva_hotel_api.entities.Habitacion;
import com.sise.reserva_hotel_api.repositories.IHabitacionRepository;
import com.sise.reserva_hotel_api.services.IHabitacionService;

import org.springframework.data.domain.Sort;

@Service
public class HabitacionServiceImpl implements IHabitacionService {

    @Autowired
    private IHabitacionRepository habitacionRepository;

    @Override
    public List<Habitacion> listarHabitaciones(){
        return habitacionRepository.findByEstadoAuditoria("1");
    }

    @Override
    public Habitacion obtenerHabitacion(Integer idHabitacion)  {
        return habitacionRepository.findOneByIdHabitacionAndEstadoAuditoria(idHabitacion, "1");
    }

    @Override
    public Habitacion insertarHabitacion(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    @Override
    public Habitacion actualizarHabitacion(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    @Override
    public void darBajaHabitacion(Integer idHabitacion) {
        habitacionRepository.darBajaHabitacion(idHabitacion);
    }
    

    @Override
    public List<Habitacion> listarHabitacionOrdenadas(Sort sort) {
        return habitacionRepository.findByEstadoAuditoria("1", sort);
    }

    @Override
    public Page<Habitacion> listarHabitacionPaginadas(Pageable pageable) {
        return habitacionRepository.findByEstadoAuditoria("1", pageable);
    }
}
