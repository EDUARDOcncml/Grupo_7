package com.sise.reserva_hotel_api.services;

import java.util.List;
import com.sise.reserva_hotel_api.entities.Huesped;

public interface IHuespedService {
    List<Huesped>listarHuesped() throws Exception;
    Huesped obtenerHuesped(Integer idHuesped) throws Exception;
    Huesped insertarHuesped(Huesped huesped) throws Exception;
    Huesped actualizarHuesped(Huesped huesped) throws Exception;
    void darBajaHuesped(Integer idHuesped) throws Exception;
}
