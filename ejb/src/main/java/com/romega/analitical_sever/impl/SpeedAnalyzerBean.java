package com.romega.analitical_sever.impl;

import jakarta.ejb.Singleton;
import com.romega.analitical_sever.remote.SpeedAnalyzer;
import com.romega.entity.Vehicle;
import jakarta.ejb.ConcurrencyManagement;
import jakarta.ejb.ConcurrencyManagementType;


import java.util.List;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class SpeedAnalyzerBean implements SpeedAnalyzer {
    @Override
    public double analyzeSpeed(List<Vehicle> vehicleList) {
        if(vehicleList == null || vehicleList.isEmpty()) {
            return 0.0;
        }

        int totalSpeed = 0;

        for(Vehicle vehicle : vehicleList) {
            totalSpeed += vehicle.getSpeed();
        }

        return (double) totalSpeed / vehicleList.size();
    }
}
