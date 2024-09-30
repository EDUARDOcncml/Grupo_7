package com.sise.reserva_hotel_api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sise.reserva_hotel_api.entities.Huesped;
import com.sise.reserva_hotel_api.repositories.IHuespedRepository;
import com.sise.reserva_hotel_api.services.IHuespedService;

@Service
public class HuespedServiceImpl implements IHuespedService {

    @Autowired
    private IHuespedRepository huespedRepository;

    @Override
    public List<Huesped> listarHuesped() throws Exception {
        return huespedRepository.findByEstadoAuditoria("1");
    }

    @Override
    public Huesped obtenerHuesped(Integer idHuesped) throws Exception {
        return huespedRepository.findOneByIdHuespedAndEstadoAuditoria(idHuesped, "1");
    }

    @Override
    public Huesped insertarHuesped(Huesped huesped) throws Exception {
        return huespedRepository.save(huesped);
    }

    @Override
    public Huesped actualizarHuesped(Huesped huesped) throws Exception {
        return huespedRepository.save(huesped);
    }

    @Override
    public void darBajaHuesped(Integer idHuesped) throws Exception {
        huespedRepository.darBajaHuesped(idHuesped);
    }

}
