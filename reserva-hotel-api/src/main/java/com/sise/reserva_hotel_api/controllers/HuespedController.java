package com.sise.reserva_hotel_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sise.reserva_hotel_api.entities.Huesped;
import com.sise.reserva_hotel_api.services.IHuespedService;
import com.sise.reserva_hotel_api.shared.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/huesped")
@Tag(name = "Huepedes", description = "API para gestionar huepedes")
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

    @GetMapping("/sorted")
    @Operation(summary = "Listar huesped ordenados")
    public ResponseEntity<BaseResponse> listarHuespedOrdenadas(
            @RequestParam(defaultValue = "idHuesped") String sortBy) {
        try {
            List<Huesped> huesped = huespedService.listarHuespedOrdenadas(Sort.by(sortBy));
            return new ResponseEntity<>(BaseResponse.success(huesped), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/paginated")
    @Operation(summary = "Listar huesped paginados y ordenados")
    public ResponseEntity<BaseResponse> listarHuespedPaginadas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Huesped> huesped = huespedService.listarHuespedPaginadas(pageable);
            return new ResponseEntity<>(BaseResponse.success(huesped), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(BaseResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
