package com.sise.reserva_hotel_api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sise.reserva_hotel_api.entities.Huesped;
import com.sise.reserva_hotel_api.repositories.IHuespedRepository;
import com.sise.reserva_hotel_api.services.IHuespedService;

@Service
public class HuespedServiceImpl implements IHuespedService {

    @Autowired
    private IHuespedRepository huespedRepository;

    @Override
    public List<Huesped> listarHuesped(){
        return huespedRepository.findByEstadoAuditoria("1");
    }

    @Override
    public Huesped obtenerHuesped(Integer idHuesped){
        return huespedRepository.findOneByIdHuespedAndEstadoAuditoria(idHuesped, "1");
    }

    @Override
    public Huesped insertarHuesped(Huesped huesped){
        return huespedRepository.save(huesped);
    }

    @Override
    public Huesped actualizarHuesped(Huesped huesped){
        return huespedRepository.save(huesped);
    }

    @Override
    public void darBajaHuesped(Integer idHuesped){
        huespedRepository.darBajaHuesped(idHuesped);
    }

    @Override
    public List<Huesped> listarHuespedOrdenadas(Sort sort) {
        return huespedRepository.findByEstadoAuditoria("1", sort);
    }

    @Override
    public Page<Huesped> listarHuespedPaginadas(Pageable pageable) {
        return huespedRepository.findByEstadoAuditoria("1", pageable);
    }

}
