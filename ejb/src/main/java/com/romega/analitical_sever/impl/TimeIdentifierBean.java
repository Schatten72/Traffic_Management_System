package com.romega.analitical_sever.impl;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.romega.analitical_sever.remote.TimeIdentifier;
import com.romega.entity.Vehicle;
import jakarta.ejb.ConcurrencyManagement;
import jakarta.ejb.ConcurrencyManagementType;
import jakarta.ejb.Singleton;



@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class TimeIdentifierBean implements TimeIdentifier {
    @Override
    public List<List<Vehicle>> specify(List<Vehicle> vehicleList) {
        List<List<Vehicle>> timeList = new ArrayList<>();
        List<Vehicle> hourGroup1 = new ArrayList<>();
        List<Vehicle> hourGroup2 = new ArrayList<>();
        List<Vehicle> hourGroup3 = new ArrayList<>();
        List<Vehicle> hourGroup4 = new ArrayList<>();

        for(Vehicle vehicle : vehicleList) {
            int hour = LocalDateTime
                    .parse(vehicle.getTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                    .getHour();

            if (hour < 7) {
                hourGroup1.add(vehicle);
            } else if (hour < 14) {
                hourGroup2.add(vehicle);
            } else if (hour < 19) {
                hourGroup3.add(vehicle);
            } else {
                hourGroup4.add(vehicle);
            }
        }

        timeList.add(hourGroup1);
        timeList.add(hourGroup2);
        timeList.add(hourGroup3);
        timeList.add(hourGroup4);

        return timeList;
    }
}
