package com.sise.reserva_hotel_api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sise.reserva_hotel_api.entities.ServicioAdicional;
import com.sise.reserva_hotel_api.repositories.IServicioRepository;
import com.sise.reserva_hotel_api.services.IServicioService;

@Service
public class ServicioServiceImpl implements IServicioService {

    @Autowired
    private IServicioRepository servicioRepository;

    @Override
    public List<ServicioAdicional> listarServicioAdicional(){
        return servicioRepository.findByEstadoAuditoria("1");
    }

    @Override
    public ServicioAdicional obtenerServicioAdicional(Integer idServicio) {
        return servicioRepository.findOneByIdServicioAndEstadoAuditoria(idServicio, "1");
    }

    @Override
    public ServicioAdicional insertarServicioAdicional(ServicioAdicional servicioAdicional){
        return servicioRepository.save(servicioAdicional);
    }

    @Override
    public ServicioAdicional actualizarServicioAdicional(ServicioAdicional servicioAdicional){
        return servicioRepository.save(servicioAdicional);
    }

    @Override
    public void darbajaServicioAdicional(Integer idServicio){
        servicioRepository.darBajaServicio(idServicio);
    }
    
    @Override
    public List<ServicioAdicional> listarServicioOrdenadas(Sort sort) {
        return servicioRepository.findByEstadoAuditoria("1", sort);
    }

    @Override
    public Page<ServicioAdicional> listarServicioPaginadas(Pageable pageable) {
        return servicioRepository.findByEstadoAuditoria("1", pageable);
    }
    
}
