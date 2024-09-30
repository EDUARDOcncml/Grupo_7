package com.sise.reserva_hotel_api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sise.reserva_hotel_api.entities.ServicioAdicional;
import com.sise.reserva_hotel_api.repositories.IServicioRepository;
import com.sise.reserva_hotel_api.services.IServicioService;

@Service
public class ServicioServiceImpl implements IServicioService {

    @Autowired
    private IServicioRepository servicioRepository;

    @Override
    public List<ServicioAdicional> listarServicioAdicional() throws Exception {
        return servicioRepository.findByEstadoAuditoria("1");
    }

    @Override
    public ServicioAdicional obtenerServicioAdicional(Integer idServicio) throws Exception {
        return servicioRepository.findOneByIdServicioAndEstadoAuditoria(idServicio, "1");
    }

    @Override
    public ServicioAdicional insertarServicioAdicional(ServicioAdicional servicioAdicional) throws Exception {
        return servicioRepository.save(servicioAdicional);
    }

    @Override
    public ServicioAdicional actualizarServicioAdicional(ServicioAdicional servicioAdicional) throws Exception {
        return servicioRepository.save(servicioAdicional);
    }

    @Override
    public void darbajaServicioAdicional(Integer idServicio) throws Exception {
        servicioRepository.darBajaServicio(idServicio);
    }
    
    
}
