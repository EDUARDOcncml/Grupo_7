package com.sise.reserva_hotel_api.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.sise.reserva_hotel_api.entities.Huesped;

public interface IHuespedService {
    List<Huesped>listarHuesped();
    Huesped obtenerHuesped(Integer idHuesped);
    Huesped insertarHuesped(Huesped huesped);
    Huesped actualizarHuesped(Huesped huesped);
    void darBajaHuesped(Integer idHuesped);

     List<Huesped> listarHuespedOrdenadas(Sort sort);
    Page<Huesped> listarHuespedPaginadas(Pageable pageable);
}
