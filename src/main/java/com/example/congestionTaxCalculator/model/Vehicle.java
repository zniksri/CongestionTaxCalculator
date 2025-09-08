package com.example.congestionTaxCalculator.model;

public  sealed interface Vehicle
        permits DefaultVehicle{
    String vehicleType();
}

