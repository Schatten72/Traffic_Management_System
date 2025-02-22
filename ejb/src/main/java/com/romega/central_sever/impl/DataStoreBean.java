package com.romega.central_sever.impl;

import com.romega.central_sever.remote.DataStore;
import com.romega.entity.TrafficIntersection;
import com.romega.entity.Vehicle;
import com.romega.util.HibernateUtil;
import jakarta.ejb.Stateless;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Stateless
public class DataStoreBean implements DataStore {
    @Override
    public void save(TrafficIntersection trafficIntersection) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(trafficIntersection);
        transaction.commit();
        session.close();
    }
    @Override
    public void save(Vehicle vehicle) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(vehicle);
        transaction.commit();
        session.close();
    }


}
