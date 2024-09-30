package com.sise.reserva_hotel_api.services;

import java.util.List;

import com.sise.reserva_hotel_api.entities.ServicioAdicional;

public interface IServicioService {
    List<ServicioAdicional>listarServicioAdicional() throws Exception;
    ServicioAdicional obtenerServicioAdicional(Integer idServicio) throws Exception;
    ServicioAdicional insertarServicioAdicional(ServicioAdicional servicioAdicional) throws Exception;
    ServicioAdicional actualizarServicioAdicional(ServicioAdicional servicioAdicional) throws Exception;
    void darbajaServicioAdicional(Integer idServicio) throws Exception;
}
