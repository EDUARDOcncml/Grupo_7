package com.sise.reserva_hotel_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sise.reserva_hotel_api.entities.Huesped;
import com.sise.reserva_hotel_api.services.IHuespedService;
import com.sise.reserva_hotel_api.shared.BaseResponse;


@RestController
@RequestMapping("/huesped")
public class HuespedController {
    
    @Autowired
    private IHuespedService huespedService;

    @GetMapping("")
    public ResponseEntity<BaseResponse> listarHuesped(){
        try {
            List<Huesped> huesped = huespedService.listarHuesped();
            return new ResponseEntity<>(BaseResponse.success(huesped),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{idHuesped}")
    public ResponseEntity<BaseResponse> obtenerHuesped(@PathVariable Integer idHuesped){
        try {
            Huesped huesped = huespedService.obtenerHuesped(idHuesped);
            if(huesped == null) return new ResponseEntity<>(BaseResponse.errorNotFound(),HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(BaseResponse.success(huesped),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<BaseResponse>insertarHuesped(@RequestBody Huesped huespedInsert) {
        try {
            Huesped huesped = huespedService.insertarHuesped(huespedInsert);
            return new ResponseEntity<>(BaseResponse.success(huesped),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{idHuesped}")
    public ResponseEntity<BaseResponse> actualizarHuesped(@PathVariable Integer idHuesped, @RequestBody Huesped huespedUpdate){
        try {          
            if(huespedService.obtenerHuesped(idHuesped) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(),HttpStatus.NOT_FOUND);
            }
            huespedUpdate.setIdHuesped(idHuesped);
            Huesped huesped = huespedService.actualizarHuesped(huespedUpdate);
            return new ResponseEntity<>(BaseResponse.success(huesped),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/dar-baja/{idHuesped}")
    public ResponseEntity<BaseResponse> darBajaHuesped(@PathVariable Integer idHuesped){
        try {
            if(huespedService.obtenerHuesped(idHuesped) == null) {
                return new ResponseEntity<>(BaseResponse.errorNotFound(),HttpStatus.NOT_FOUND);
            }
            huespedService.darBajaHuesped(idHuesped);
            return new ResponseEntity<>(BaseResponse.success(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
