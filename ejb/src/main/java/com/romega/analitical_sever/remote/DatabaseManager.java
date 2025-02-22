package com.romega.analitical_sever.remote;

import com.romega.entity.TrafficIntersection;
import com.romega.entity.Vehicle;
import com.romega.entity.AverageVehicleSpeed;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface DatabaseManager {
    void saveAverageSpeed(AverageVehicleSpeed averageSpeed);

    List<Vehicle> getVehiclesList(String date);

    List<TrafficIntersection> getTrafficIntersectionsList(String date);
}
