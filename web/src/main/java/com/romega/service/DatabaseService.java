package com.romega.service;

import com.romega.analitical_sever.remote.DatabaseManager;
import com.romega.entity.TrafficIntersection;
import com.romega.entity.Vehicle;
import com.romega.entity.AverageVehicleSpeed;
import jakarta.ejb.EJB;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class DatabaseService {
    private DatabaseManager databaseManager;

    public DatabaseService(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void save(AverageVehicleSpeed averageSpeed) {
//        System.out.println("alll!!");
//        System.out.println("avgSpeed" + averageSpeed.getAvg_speed());
//        System.out.println("ID" + averageSpeed.getId());
//        System.out.println("Date" + averageSpeed.getDate());
//        System.out.println("Time" + averageSpeed.getTime_period());
        databaseManager.saveAverageSpeed(averageSpeed);
    }

    public List<Vehicle> fetchVehiclesList() {
        return databaseManager.getVehiclesList(today());
    }

    public List<TrafficIntersection> fetchTrafficIntersectionsList() {
        return databaseManager.getTrafficIntersectionsList(currentDateTime());
    }

    private String today() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(new Date());
        return today;
    }

    private String currentDateTime() {
        LocalDateTime dateTime = LocalDateTime.now();

        if(dateTime.getMinute() % 2 != 0) {
            dateTime = dateTime.minusMinutes(1);
        }

        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
