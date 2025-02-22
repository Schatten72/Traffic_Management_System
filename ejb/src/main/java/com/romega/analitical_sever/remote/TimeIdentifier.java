package com.romega.analitical_sever.remote;

import com.romega.entity.Vehicle;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface TimeIdentifier {
    List<List<Vehicle>> specify(List<Vehicle> vehicleList);
}
