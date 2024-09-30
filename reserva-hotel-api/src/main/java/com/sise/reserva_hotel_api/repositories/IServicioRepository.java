package com.sise.reserva_hotel_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.sise.reserva_hotel_api.entities.ServicioAdicional;

@Repository
public interface IServicioRepository extends JpaRepository<ServicioAdicional, Integer>{

    List<ServicioAdicional> findByEstadoAuditoria(String estadoAuditoria);

    ServicioAdicional findOneByIdServicioAndEstadoAuditoria(Integer idServicio, String estadoAuditoria);
    
    @Modifying
    @Transactional
    @Query("UPDATE ServicioAdicional s Set s.estadoAuditoria = '0' WHERE s.idServicio = :idServicio")
    Void darBajaServicio(@Param("idServicio") Integer idServicio);

}
