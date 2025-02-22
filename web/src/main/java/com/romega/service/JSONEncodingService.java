package com.romega.service;

import com.romega.entity.TrafficIntersection;
import com.romega.entity.AverageVehicleSpeed;
import com.romega.entity.TrafficLevel;
import com.romega.entity.Location;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

import java.util.List;
import java.util.Map;

public class JSONEncodingService {
    public String getJsonResponse(Map<String, AverageVehicleSpeed> averageSpeedMap,
                                  Map<String, TrafficLevel> congestionLevelMap,
                                  List<TrafficIntersection> trafficIntersectionList) {

        return getJsonObject(averageSpeedMap, congestionLevelMap, trafficIntersectionList).toString();
    }

    private JsonObject getJsonObject(Map<String, AverageVehicleSpeed> averageSpeedMap,
                                     Map<String, TrafficLevel> congestionLevelMap,
                                     List<TrafficIntersection> trafficIntersectionList) {

        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("averageSpeed", getAverageSpeedObject(averageSpeedMap));
        jsonObjectBuilder.add("congestionLevel", getCongestionLevelObject(congestionLevelMap));
        jsonObjectBuilder.add("trafficIntersection", getTrafficIntersectionObject(trafficIntersectionList));

        return jsonObjectBuilder.build();
    }

    private JsonObject getAverageSpeedObject(Map<String, AverageVehicleSpeed> averageSpeedMap) {
        JsonObjectBuilder averageSpeedBuilder = Json.createObjectBuilder();

        for(Map.Entry<String, AverageVehicleSpeed> entry : averageSpeedMap.entrySet()) {
            averageSpeedBuilder.add(entry.getKey(), String.valueOf(entry.getValue().getAvg_speed()));
        }
        return averageSpeedBuilder.build();
    }

    private JsonObject getCongestionLevelObject(Map<String, TrafficLevel> congestionLevelMap) {
        JsonObjectBuilder congestionLevelBuilder = Json.createObjectBuilder();

        for(Map.Entry<String, TrafficLevel> entry : congestionLevelMap.entrySet()) {
            congestionLevelBuilder.add(entry.getKey(), entry.getValue().toString());
        }
        return congestionLevelBuilder.build();
    }

    private JsonObject getTrafficIntersectionObject(List<TrafficIntersection> trafficIntersectionList) {
        JsonObjectBuilder trafficIntersectionBuilder = Json.createObjectBuilder();

        for(TrafficIntersection trafficIntersection : trafficIntersectionList) {
            String trafficIntersectionId = identifyTrafficIntersection(trafficIntersection.getCoordinates());
            trafficIntersectionBuilder.add(trafficIntersectionId, trafficIntersection.getStatus().toString());
        }

        return trafficIntersectionBuilder.build();
    }

    private String identifyTrafficIntersection(Location coordinates) {
        double t1_latitude = 7.429981483737187;
        double t1_longitude = 80.44820444218345;
        double t2_latitude = 7.430321923688058;
        double t2_longitude =  80.4484082900562;

        Location t1_coordinate = new Location(t1_latitude, t1_longitude);
        Location t2_coordinate = new Location(t2_latitude, t2_longitude);

        if(coordinates.compareTo(t1_coordinate) == 1) {
            return "t1";
        }

        if(coordinates.compareTo(t2_coordinate) == 1) {
            return "t2";
        }
        return null;
    }
}
