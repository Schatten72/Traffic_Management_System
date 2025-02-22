package com.romega.central_sever.remote;

import com.romega.entity.TrafficIntersection;
import com.romega.entity.Vehicle;
import jakarta.ejb.Remote;

@Remote
public interface DataStore {
    void save(Vehicle vehicle);

    void save(TrafficIntersection trafficIntersection);
}
