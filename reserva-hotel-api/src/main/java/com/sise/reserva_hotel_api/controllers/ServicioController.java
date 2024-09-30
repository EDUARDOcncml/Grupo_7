package com.sise.reserva_hotel_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sise.reserva_hotel_api.entities.ServicioAdicional;
import com.sise.reserva_hotel_api.services.IServicioService;
import com.sise.reserva_hotel_api.shared.BaseResponse;

@RestController
@RequestMapping("/servicio-adicional")
public class ServicioController {

    @Autowired
    private IServicioService servicioService;

    @GetMapping("")
    public ResponseEntity<BaseResponse> listarServicioAdicional() {
        try {
            List<ServicioAdicional> servicioAdicional = servicioService.listarServicioAdicional();
            return new ResponseEntity<>(BaseResponse.success(servicioAdicional), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{idServicio}")
    public ResponseEntity<BaseResponse> obtenerServicioAdicional(@PathVariable Integer idServicio) {
        try {
            ServicioAdicional servicioAdicional = servicioService.obtenerServicioAdicional(idServicio);
            if(servicioAdicional == null) return new ResponseEntity<>(BaseResponse.errorNotFound(),HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(BaseResponse.success(servicioAdicional), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse> insertarServicioAdicional(@RequestBody ServicioAdicional servicioAdicionalInsert) {
        try {
            ServicioAdicional servicioAdicional = servicioService.insertarServicioAdicional(servicioAdicionalInsert);
            return new ResponseEntity<>(BaseResponse.success(servicioAdicional), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{idServicio}")
    public ResponseEntity<BaseResponse> actualizarServicioAdicional(@PathVariable Integer idServicio, @RequestBody ServicioAdicional servicioAdicionalUpdate) {
        try {

            if(servicioService.obtenerServicioAdicional(idServicio) == null){
                return new ResponseEntity<>(BaseResponse.errorNotFound(),HttpStatus.NOT_FOUND);
            } 
            servicioAdicionalUpdate.setIdServicio(idServicio);
            ServicioAdicional servicioAdicional = servicioService.actualizarServicioAdicional(servicioAdicionalUpdate);
            return new ResponseEntity<>(BaseResponse.success(servicioAdicional), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/dar-baja/{idServicio}")
    public ResponseEntity<BaseResponse> darbajaServicioAdicional(@PathVariable Integer idServicio){
        try {
            if(servicioService.obtenerServicioAdicional(idServicio) == null){
                return new ResponseEntity<>(BaseResponse.errorNotFound(),HttpStatus.NOT_FOUND);
            } 
            servicioService.darbajaServicioAdicional(idServicio);
            return new ResponseEntity<>(BaseResponse.success(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
