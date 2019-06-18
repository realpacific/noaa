package com.realpacific.projectnoaa.repositories;

import com.realpacific.projectnoaa.connection.HibernateUtils;
import com.realpacific.projectnoaa.entities.Gsod;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class GsodRepository {
    private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    @Transactional
    public void bulkSave(List<Gsod> gsods) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            for (Gsod gsod : gsods) {
                session.save(gsod);
            }
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
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
        Query<Gsod> query = session
                .createNativeQuery("SELECT g.* FROM tbl_record AS r INNER JOIN tbl_gsod AS g ON g.wban = r.wban AND g.stationNumber != '999999' AND g.wban != '99999' WHERE g.date like ?0 AND  r.country like ?1", Gsod.class);
        query.setParameter(0, date);
        query.setParameter(1, country);
        List<Gsod> gsods = query.list();
        session.getTransaction().commit();
        session.close();
        return gsods;
    }


    @Transactional
    public List<Gsod> findAllGsodsByStationNameAndDate(String stationName, String date) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query<Gsod> query = session.createNativeQuery("SELECT g.* FROM tbl_record AS r INNER JOIN tbl_gsod AS g ON g.wban = r.wban AND g.stationNumber != '999999' AND g.wban != '99999' WHERE g.date like ?0 AND  r.stationName like ?1", Gsod.class);
        query.setParameter(0, date)
                .setParameter(1, stationName);
        List<Gsod> gsods = query.list();
        session.getTransaction().commit();
        session.close();
        return gsods;
    }


}
