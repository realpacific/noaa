package com.realpacific.projectnoaa.repositories;

import com.realpacific.projectnoaa.connection.HibernateUtils;
import com.realpacific.projectnoaa.entities.Gsod;
import com.realpacific.projectnoaa.exceptions.DataFetchException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Transactional
public class GsodRepository {
    private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    @Transactional
    public void saveOne(Gsod gsod) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.save(gsod);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new DataFetchException(e.getMessage());
        }
    }

    @Transactional
    public List<Gsod> findAllGsods() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            return session.createQuery("SELECT g FROM Gsod g", Gsod.class).list();
        } catch (HibernateException e) {
            throw new DataFetchException(e.getMessage());
        }
    }

    @Transactional
    public List<Gsod> findAllGsodsByIdAndDate(String id, String date) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<Gsod> query = session.createQuery("SELECT g FROM Gsod g WHERE g.stationNumber like :id AND g.date like :date", Gsod.class);
            query.setParameter("date", date);
            query.setParameter("id", id);
            return query.list();
        } catch (HibernateException e) {
            throw new DataFetchException(e.getMessage());
        }
    }


    @Transactional
    public List<Gsod> findAllGsodsByCountryAndDate(String country, String date) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<Gsod> query = session.createQuery("SELECT g FROM Gsod g JOIN FETCH g.station s WHERE g.date LIKE :date AND  s.country like :country", Gsod.class);
            query.setParameter("date", date);
            query.setParameter("country", country);
            return query.list();
        } catch (HibernateException e) {
            throw new DataFetchException(e.getMessage());
        }
    }


    @Transactional
    public List<Gsod> findAllGsodsByStationNameAndDate(String stationName, String date) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<Gsod> query = session.createQuery("SELECT g FROM Gsod g JOIN FETCH g.station s WHERE g.date LIKE :date AND  s.stationName like :stationName", Gsod.class);
            query.setParameter("date", date)
                    .setParameter("stationName", stationName);
            return query.list();
        } catch (HibernateException e) {
            throw new DataFetchException(e.getMessage());
        }
    }


    public List<String> findAllAvailableDates() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            NativeQuery<String> query = session.createNativeQuery("SELECT DISTINCT(g.date) FROM tbl_gsod AS g");
            List<String> dates = query.list();
            return dates;
        } catch (HibernateException e) {
            throw new DataFetchException(e.getMessage());
        }
    }
}
