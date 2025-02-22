package com.romega.analitical_sever.impl;

import com.romega.analitical_sever.remote.TrafficAnalyzer;
import com.romega.entity.TrafficLevel;
import jakarta.ejb.Singleton;

@Singleton
public class TrafficAnalyzerBean implements TrafficAnalyzer {
    private static final int MAXIMUM_SPEED = 60;

    @Override
    public TrafficLevel analyzeTrafficPattenr(double averageSpeed) {
        double trafficScore = averageSpeed/MAXIMUM_SPEED;

        if (trafficScore >= 0.75) { // above 45 KmPH
            return TrafficLevel.LIGHT;
        } else if (trafficScore >= 0.25) { // between 15-45 KmPH
            return TrafficLevel.NORMAL;
        } else if (trafficScore > 0) { //below 15 KmPH
            return TrafficLevel.HEAVY;
        } else {
            return TrafficLevel.NULL;
        }
    }
}
