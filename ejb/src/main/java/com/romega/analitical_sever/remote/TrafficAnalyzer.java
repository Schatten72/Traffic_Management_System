package com.romega.analitical_sever.remote;

import com.romega.entity.TrafficLevel;
import jakarta.ejb.Remote;

@Remote
public interface TrafficAnalyzer {
    TrafficLevel analyzeTrafficPattenr(double averageSpeed);
}
