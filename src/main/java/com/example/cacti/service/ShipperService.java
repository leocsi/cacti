package com.example.cacti.service;

import com.example.cacti.controller.ShipperNotFoundException;
import com.example.cacti.model.Shipper;
import com.example.cacti.repository.ShipperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipperService {
    @Autowired
    private ShipperRepository shipperRepository;

    public List<Shipper> readAllShippers() {
        return shipperRepository.findAll();
    }

    public Shipper readShipperById(Long id) {
        return shipperRepository.findById(id).orElseThrow(() -> new ShipperNotFoundException("No Shipper with  id: [ " + id + "]"));
    }

    public void addNewShipper(Shipper shipper) {
        shipperRepository.save(shipper);
    }

    public void deleteShipper(Shipper shipper) {
        shipperRepository.delete(shipper);
    }
    public void deleteShipperByID(Long id){
        shipperRepository.deleteById(id);
    }
    public List<String> getPhoneBook() {
        List<String> phoneList = new ArrayList<>();
        for (Shipper shipper : shipperRepository.findAll()) {
            phoneList.add(shipper.getPhone());
        }
        return phoneList;
    }

    public List<String> getNames() {
        List<String> names = new ArrayList<>();
        for (Shipper s : shipperRepository.findAll()) {
            names.add(s.getName());
        }
        return names;
    }

    public List<String> getLocalPhoneNumbers() {
        List<String> locals = new ArrayList<>();
        for(Shipper s : shipperRepository.findAll()) {
            if(s.getPhone().startsWith("(503)")) locals.add(s.getPhone());
        }
        return locals;
    }

    public Shipper updateShipper(Long id, Shipper shipper) {
        // Throw exception for non-existing id
        this.readShipperById(id);
        shipper.setId(id);
        return shipperRepository.save(shipper);
    }

    public void deleteShipperBy(Long id) {
        // Throw exception for non-existing id
        this.readShipperById(id);

        shipperRepository.deleteById(id);
    }

}