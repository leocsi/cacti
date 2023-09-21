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
    public List<String> getPhoneBook() {
        List<String> phoneList = new ArrayList<>();
        for (Shipper shipper : shipperRepository.findAll()) {
            phoneList.add(shipper.getPhone());
        }
        return phoneList;
    }
}