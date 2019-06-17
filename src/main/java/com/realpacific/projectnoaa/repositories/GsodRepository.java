package com.realpacific.projectnoaa.repositories;

import com.realpacific.projectnoaa.connection.HibernateUtils;
import com.realpacific.projectnoaa.entities.Gsod;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.List;

public class GsodRepository {
    private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    @Transactional
    public void bulkSave(List<Gsod> gsods) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        for (Gsod gsod : gsods) {
            session.save(gsod);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Transactional
    public List<Gsod> findAllGsods() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Gsod> gsods = session.createQuery("SELECT g FROM Gsod g", Gsod.class).list();
        session.getTransaction().commit();
        session.close();
        return gsods;

    }

    @Transactional
    public List<Gsod> findAllGsodsByIdAndDate(String id, String date) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query<Gsod> query = session.createQuery("SELECT g FROM Gsod g WHERE g.stationNumber like :id AND g.date like :date", Gsod.class);
        query.setParameter("date", date);
        query.setParameter("id", id);
        List<Gsod> gsods = query.list();
        session.getTransaction().commit();
        session.close();
        return gsods;
    }


    @Transactional
    public List<Gsod> findAllGsodsByCountryAndDate(String country, String date) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query<Gsod> query = session.createQuery("SELECT g FROM Gsod g, Record r WHERE g.date =:date AND  r.country like :country", Gsod.class);
        query.setParameter("date", date)
                .setParameter("country", country);
        List<Gsod> gsods = query.list();
        session.getTransaction().commit();
        session.close();
        return gsods;
    }


    @Transactional
    public List<Gsod> findAllGsodsByStationNameAndDate(String stationName, String date) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query<Gsod> query = session.createQuery("SELECT g FROM Gsod g, Record r WHERE g.date =:date AND  r.stationName like :stationName", Gsod.class);
        query.setParameter("date", date)
                .setParameter("stationName", stationName);
        List<Gsod> gsods = query.list();
        session.getTransaction().commit();
        session.close();
        return gsods;
    }


}
