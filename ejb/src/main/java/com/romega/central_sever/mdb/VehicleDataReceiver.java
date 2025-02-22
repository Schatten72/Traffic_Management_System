package com.romega.central_sever.mdb;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;
import com.romega.central_sever.remote.DataStore;
import com.romega.entity.Vehicle;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.EJB;
import jakarta.ejb.MessageDriven;


@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "tmsVehicleQueue")
        }
)
public class VehicleDataReceiver implements MessageListener {

    @EJB
    DataStore datastore;

    @Override
    public void onMessage(Message message) {
        try {
            Vehicle vehicle = (Vehicle) ((ObjectMessage) message).getObject();

            datastore.save(vehicle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
