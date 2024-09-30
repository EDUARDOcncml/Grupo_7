package com.sise.reserva_hotel_api.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Data
@Entity
@Table(name = "huesped")
public class Huesped {
    
    @Id
    @Column(name = "id_huesped")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHuesped;

    @Column(name = "dni")
    private Integer dni;

    @Column(name = "nombres")
    private String nombres;
    
    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "fecha_naci", insertable = false, updatable = false)
    private LocalDateTime fechaNaci;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private Integer telefono;

    @Column(name = "email")
    private String email;

    @Column(name = "estado_auditoria", insertable = false, updatable = false)
    @JsonIgnore
    private String estadoAuditoria;

}
