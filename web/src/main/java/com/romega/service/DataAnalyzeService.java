package com.romega.service;

import com.romega.analitical_sever.remote.*;
import com.romega.entity.Vehicle;
import com.romega.entity.AverageVehicleSpeed;
import com.romega.entity.TrafficLevel;
import jakarta.ejb.EJB;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataAnalyzeService {
    private LocationAnalyzer routeSpecifier;
    private TimeIdentifier timeSpecifier;
    private SpeedAnalyzer speedAnalyzer;
    private TrafficAnalyzer congestionAnalyzer;

    public DataAnalyzeService(LocationAnalyzer routeSpecifier, TimeIdentifier timeSpecifier,
                              SpeedAnalyzer speedAnalyzer, TrafficAnalyzer congestionAnalyzer) {
        this.routeSpecifier = routeSpecifier;
        this.timeSpecifier = timeSpecifier;
        this.speedAnalyzer = speedAnalyzer;
        this.congestionAnalyzer = congestionAnalyzer;
    }

    public Map<String, TrafficLevel> analyzeCongestion(Map<String, AverageVehicleSpeed> congestionDataMap) {
        Map<String, TrafficLevel> congestionLevelMap = new HashMap<>();

        for (Map.Entry<String, AverageVehicleSpeed> entry : congestionDataMap.entrySet()) {
            String key = entry.getKey();
            double averageSpeed = entry.getValue().getAvg_speed();

            TrafficLevel level = congestionAnalyzer.analyzeTrafficPattenr(averageSpeed);

            congestionLevelMap.put(key, level);
        }

        return congestionLevelMap;
    }

    public Map<String, AverageVehicleSpeed> getAverageSpeedMap(List<Vehicle> vehicleList) {
        Map<String, AverageVehicleSpeed> averageSpeedMap = new HashMap<>();

        // Get Route List
        List<List<Vehicle>> routeList = specifyRoute(vehicleList);



        // Get Time List
        for(int i=0; i<routeList.size(); i++) {
            List<List<Vehicle>> timeList = specifyTime(routeList.get(i));



            // Get Average Speed List
            for(int j=0; j<timeList.size(); j++) {
                List<Vehicle> groupedVehicleList = timeList.get(j);
                double averageSpeedValue = analyzeSpeed(groupedVehicleList);


                AverageVehicleSpeed averageSpeed = new AverageVehicleSpeed();

                System.out.println(averageSpeed);
                averageSpeed.setAvg_speed(averageSpeedValue);
                averageSpeed.setDate(getDate());
                System.out.println("getDate+++"+getDate());
                System.out.println("Averageget Date:"+averageSpeed.getDate());
                averageSpeed.setTime_period(i+1);

                String key = "r" + (i+1) + "_t" + (j+1);

                averageSpeedMap.put(key, averageSpeed);


            }
        }

        return averageSpeedMap;
    }

    private List<List<Vehicle>> specifyRoute(List<Vehicle> vehicleList) {
        return routeSpecifier.specify(vehicleList);
    }

    private List<List<Vehicle>> specifyTime(List<Vehicle> vehicleList) {
        return timeSpecifier.specify(vehicleList);
    }

    private double analyzeSpeed(List<Vehicle> vehicleList) {
        return speedAnalyzer.analyzeSpeed(vehicleList);
    }

    private Date getDate() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String today = sdf.format(new Date());
            return sdf.parse(today);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
