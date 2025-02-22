package com.romega.servlet;

import com.romega.analitical_sever.remote.*;
import com.romega.entity.TrafficIntersection;
import com.romega.entity.Vehicle;
import com.romega.entity.AverageVehicleSpeed;
import com.romega.entity.TrafficLevel;
import com.romega.service.DataAnalyzeService;
import com.romega.service.DatabaseService;
import com.romega.service.JSONEncodingService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "DataReceiver", value = "/dataReceiver")
public class DataReceiver extends HttpServlet {
    @EJB
    DatabaseManager databaseManager;
    @EJB
    LocationAnalyzer routeSpecifier;
    @EJB
    TimeIdentifier timeSpecifier;
    @EJB
    SpeedAnalyzer speedAnalyzer;
    @EJB
    TrafficAnalyzer congestionAnalyzer;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        DatabaseService databaseService = new DatabaseService(databaseManager);
        DataAnalyzeService analyzeService = new DataAnalyzeService(routeSpecifier, timeSpecifier, speedAnalyzer, congestionAnalyzer);

        // Get Vehicle List
        List<Vehicle> vehicleList = databaseService.fetchVehiclesList();


        // Analyze Average Speed
        // Return 8 entries (2 routes, 4 timelines)
        Map<String, AverageVehicleSpeed> averageSpeedMap = analyzeService.getAverageSpeedMap(vehicleList);

        System.out.println("average speed!!!!!" + averageSpeedMap.size());
        // Save Average Speed List
        for(Map.Entry<String, AverageVehicleSpeed> entry : averageSpeedMap.entrySet()) {
            AverageVehicleSpeed averageSpeed = entry.getValue();
            System.out.println("Get Average"+averageSpeed.getAvg_speed());
            databaseService.save(averageSpeed);
        }

        // Analyze Traffic level
        Map<String, TrafficLevel> congestionLevelMap = analyzeService.analyzeCongestion(averageSpeedMap);

        // Get Traffic Intersection List
        List<TrafficIntersection> trafficIntersectionList = databaseService.fetchTrafficIntersectionsList();

        // Get Json String
        JSONEncodingService jsonService = new JSONEncodingService();
        String jsonString = jsonService.getJsonResponse(averageSpeedMap, congestionLevelMap, trafficIntersectionList);
        response.getWriter().print(jsonString);
    }
}
