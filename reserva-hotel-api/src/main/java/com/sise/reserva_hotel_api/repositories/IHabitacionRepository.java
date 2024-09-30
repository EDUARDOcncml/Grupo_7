package com.sise.reserva_hotel_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sise.reserva_hotel_api.entities.Habitacion;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Repository
public interface IHabitacionRepository extends JpaRepository<Habitacion, Integer> {

    List<Habitacion> findByEstadoAuditoria(String estadoAuditoria);

    Habitacion findOneByIdHabitacionAndEstadoAuditoria(Integer idHabitacion, String estadoAuditoria);

    List<Habitacion> findByEstadoAuditoria(String estadoAuditoria, Sort sort);
    Page<Habitacion> findByEstadoAuditoria(String estadoAuditoria, Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE Habitacion h SET h.estadoAuditoria = '0' WHERE h.idHabitacion = :idHabitacion")
    Void darBajaHabitacion(@Param("idHabitacion") Integer idHabitacion);
    
    
}
