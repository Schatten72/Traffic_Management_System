package com.romega.analitical_sever.impl;

import com.romega.analitical_sever.remote.DatabaseManager;
import com.romega.entity.TrafficIntersection;
import com.romega.entity.Vehicle;
import com.romega.entity.AverageVehicleSpeed;
import com.romega.util.HibernateUtil;
import jakarta.ejb.ConcurrencyManagement;
import jakarta.ejb.ConcurrencyManagementType;
import jakarta.ejb.Singleton;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class DatabaseManageBean implements DatabaseManager {
    @Override
    public void saveAverageSpeed(AverageVehicleSpeed averageSpeed) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(averageSpeed);
        transaction.commit();
        session.close();
    }
    @Override
    public List<TrafficIntersection> getTrafficIntersectionsList(String time) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            return session.createQuery("SELECT t FROM TrafficIntersection t WHERE time = :time", TrafficIntersection.class)
                    .setParameter("time", time)
                    .list();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public List<Vehicle> getVehiclesList(String date) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            return session.createQuery("SELECT v FROM Vehicle v WHERE v.time LIKE :date", Vehicle.class)
                    .setParameter("date", date + "%")
                    .list();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }


}
