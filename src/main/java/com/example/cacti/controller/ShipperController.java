package com.example.cacti.controller;

import com.example.cacti.model.Shipper;
import com.example.cacti.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/add")
    ResponseEntity<String> addNewShipper(@RequestBody Shipper shipper) {
        try {
            shipperService.addNewShipper(shipper);
            return ResponseEntity.ok("New shipper added");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @DeleteMapping("/delete")
    ResponseEntity<String> deleteShipper(@RequestBody Shipper shipper) {
        try{
            shipperService.deleteShipper(shipper);
            return ResponseEntity.ok("Shipper deleted");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }
    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteShipper(@PathVariable Long id) {
        try{
            shipperService.deleteShipperByID(id);
            return ResponseEntity.ok("Shipper Deleted");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @GetMapping("/phoneBook")
    List<String> readShipperPhoneList() {
        try {
            return shipperService.getPhoneBook();
        } catch (ShipperNotFoundException ex) {
            throw new ResponseStatusException(NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/nameBook")
    List<String> readShipperNames() {
        try {
            return shipperService.getNames();
        } catch (ShipperNotFoundException e) {
            throw new ResponseStatusException(NOT_FOUND, e.getMessage());
        }

    }

}
