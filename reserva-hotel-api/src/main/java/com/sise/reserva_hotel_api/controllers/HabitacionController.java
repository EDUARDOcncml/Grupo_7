package com.sise.reserva_hotel_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sise.reserva_hotel_api.entities.Habitacion;
import com.sise.reserva_hotel_api.services.IHabitacionService;
import com.sise.reserva_hotel_api.shared.BaseResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/habitaciones")
public class HabitacionController {

    @Autowired
    private IHabitacionService habitacionService;

    @GetMapping("")
    public ResponseEntity<BaseResponse> listarHabitaciones() {
        try {
            List<Habitacion> habitacion = habitacionService.listarHabitaciones();
            return new ResponseEntity<>(BaseResponse.success(habitacion), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idHabitacion}")
    public ResponseEntity<BaseResponse> obtenerHabitacion(@PathVariable Integer idHabitacion) {
        try {
            Habitacion habitacion = habitacionService.obtenerHabitacion(idHabitacion);
            if(habitacion == null) return new ResponseEntity<>(BaseResponse.errorNotFound(),HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(BaseResponse.success(habitacion), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("")
    public ResponseEntity<BaseResponse> insertarHabitacion(@RequestBody Habitacion habitacionInsert) {
        try {
            Habitacion habitacion = habitacionService.insertarHabitacion(habitacionInsert);
            return new ResponseEntity<>(BaseResponse.success(habitacion), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{idHabitacion}")
    public ResponseEntity<BaseResponse> actualizarHabitacion(@PathVariable Integer idHabitacion, @RequestBody Habitacion habitacionUpdate) {
        try {
            if(habitacionService.obtenerHabitacion(idHabitacion) == null){
                return new ResponseEntity<>(BaseResponse.errorNotFound(),HttpStatus.NOT_FOUND);
            }
            habitacionUpdate.setIdHabitacion(idHabitacion);
            Habitacion habitacion = habitacionService.actualizarHabitacion(habitacionUpdate);
            return new ResponseEntity<>(BaseResponse.success(habitacion), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PatchMapping("/dar-baja/{idHabitacion}")
    public ResponseEntity<BaseResponse> darBajaHabitacion(@PathVariable Integer idHabitacion) {
        try {
            if(habitacionService.obtenerHabitacion(idHabitacion) == null){
                return new ResponseEntity<>(BaseResponse.errorNotFound(),HttpStatus.NOT_FOUND);
            }
            habitacionService.darBajaHabitacion(idHabitacion);
            return new ResponseEntity<>(BaseResponse.success(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
}