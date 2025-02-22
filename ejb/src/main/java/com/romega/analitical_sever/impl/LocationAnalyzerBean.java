package com.romega.analitical_sever.impl;

import com.romega.analitical_sever.remote.LocationAnalyzer;
import com.romega.entity.Vehicle;
import com.romega.entity.Location;
import jakarta.ejb.ConcurrencyManagement;
import jakarta.ejb.ConcurrencyManagementType;
import jakarta.ejb.Singleton;

import java.util.ArrayList;
import java.util.List;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class LocationAnalyzerBean implements LocationAnalyzer {
    private final static double R1_startX = 7.429853818687599;
    private final static double R1_startY = 80.44800729983284;
    private final static double R1_endX =7.430107818906;
    private final static double R1_endY = 80.44877307046004;

    private final static double R2_startX =  7.429857808220969;
    private final static double R2_startY =   80.44813336364888;
    private final static double R2_endX =  7.430621138270807;
    private final static double R2_endY = 80.44856385816959;

    @Override
    public List<List<Vehicle>> specify(List<Vehicle> vehicleList) {
        List<List<Vehicle>> roadList = new ArrayList<>();
        List<Vehicle> roadR1List = new ArrayList<>();
        List<Vehicle> roadR2List = new ArrayList<>();

        Location coordinates;

        for(Vehicle vehicle : vehicleList) {
            coordinates = vehicle.getCoordinates();
            String road = identifyroad(coordinates.getLatitude(), coordinates.getLongitude());

            if(road.equals("R1")) {
                roadR1List.add(vehicle);
            } else if(road.equals("R2")) {
                roadR2List.add(vehicle);
            }
        }
        roadList.add(roadR1List);
        roadList.add(roadR2List);

        return roadList;
    }

    private String identifyroad(double pointX, double pointY) {
        double distanceToR1 = distanceToLineSegment(R1_startX, R1_startY, R1_endX, R1_endY, pointX, pointY);
        double distanceToR2 = distanceToLineSegment(R2_startX, R2_startY, R2_endX, R2_endY, pointX, pointY);

        if (distanceToR1 < distanceToR2) {
            return "R1";
        } else {
            return "R2";
        }
    }

    private double distanceToLineSegment(double startX, double startY, double endX, double endY, double pointX, double pointY) {
        double lengthSquared = (endX - startX) * (endX - startX) + (endY - startY) * (endY - startY);
        double sqrt = Math.sqrt((pointX - startX) * (pointX - startX) + (pointY - startY) * (pointY - startY));

        if (lengthSquared == 0.0) {
            return sqrt;
        }

        double t = ((pointX - startX) * (endX - startX) + (pointY - startY) * (endY - startY)) / lengthSquared;

        if (t < 0) {
            return sqrt;
        }

        if (t > 1) {
            return Math.sqrt((pointX - endX) * (pointX - endX) + (pointY - endY) * (pointY - endY));
        }

        double projectionX = startX + t * (endX - startX);
        double projectionY = startY + t * (endY - startY);

        return Math.sqrt((pointX - projectionX) * (pointX - projectionX) + (pointY - projectionY) * (pointY - projectionY));
    }
}
