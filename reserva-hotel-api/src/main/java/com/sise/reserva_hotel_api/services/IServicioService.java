package com.sise.reserva_hotel_api.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.sise.reserva_hotel_api.entities.ServicioAdicional;

public interface IServicioService {
    List<ServicioAdicional>listarServicioAdicional();
    ServicioAdicional obtenerServicioAdicional(Integer idServicio);
    ServicioAdicional insertarServicioAdicional(ServicioAdicional servicioAdicional);
    ServicioAdicional actualizarServicioAdicional(ServicioAdicional servicioAdicional);
    void darbajaServicioAdicional(Integer idServicio);

    List<ServicioAdicional> listarServicioOrdenadas(Sort sort);
    Page<ServicioAdicional> listarServicioPaginadas(Pageable pageable);
}
