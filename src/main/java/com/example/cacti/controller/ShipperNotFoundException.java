package com.example.cacti.controller;


public class ShipperNotFoundException extends RuntimeException {
    public ShipperNotFoundException(String message) {
        super(message);
    }
}