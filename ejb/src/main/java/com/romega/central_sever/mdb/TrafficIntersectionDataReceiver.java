package com.romega.central_sever.mdb;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;

import com.romega.entity.TrafficIntersection;
import com.romega.central_sever.remote.DataStore;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.EJB;
import jakarta.ejb.MessageDriven;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "tmsTrafficQueue")
        }
)
public class TrafficIntersectionDataReceiver implements MessageListener {

    @EJB
    DataStore datastore;

    @Override
    public void onMessage(Message message) {
        try {
            TrafficIntersection trafficIntersection = (TrafficIntersection) ((ObjectMessage) message).getObject();

            datastore.save(trafficIntersection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
