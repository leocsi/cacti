package com.example.cacti.controller;

import com.example.cacti.model.Shipper;
import com.example.cacti.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("api/shippers")
public class ShipperController {

    @Autowired
    private ShipperService shipperService;

    @GetMapping
    List<Shipper> getAll() {
        return shipperService.readAllShippers();
    }
    @GetMapping("/{id}")
    Shipper getById(@PathVariable Long id) {
        try {
            return shipperService.readShipperById(id);
        } catch (ShipperNotFoundException ex) {
            throw new ResponseStatusException(NOT_FOUND, ex.getMessage());
        }
    }
}
