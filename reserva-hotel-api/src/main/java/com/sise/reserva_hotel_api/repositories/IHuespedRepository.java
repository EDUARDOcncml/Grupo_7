package com.sise.reserva_hotel_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sise.reserva_hotel_api.entities.Huesped;
import java.util.List;

@Repository
public interface IHuespedRepository extends JpaRepository<Huesped, Integer> {

    List<Huesped> findByEstadoAuditoria(String estadoAuditoria);

    Huesped findOneByIdHuespedAndEstadoAuditoria(Integer idHuesped, String estadoAuditoria);

    @Modifying
    @Transactional
    @Query("UPDATE Huesped h SET h.estadoAuditoria = '0' WHERE h.idHuesped = :idHuesped")
    void darBajaHuesped(@Param("idHuesped") Integer idHuesped);
    
}
