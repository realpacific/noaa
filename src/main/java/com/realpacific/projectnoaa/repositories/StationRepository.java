package com.realpacific.projectnoaa.repositories;

import com.realpacific.projectnoaa.connection.HibernateUtils;
import com.realpacific.projectnoaa.entities.Station;
import com.realpacific.projectnoaa.exceptions.DataFetchException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StationRepository {
    private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    @Transactional
    public Station findStationById(String usafId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            return session.get(Station.class, usafId);
        } catch (HibernateException e) {
            throw new DataFetchException(e.getMessage());
        }
    }

    @Transactional
    public void saveOne(Station station) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            saveIfValid(station, session);
        } catch (HibernateException e) {
            throw new DataFetchException(e.getMessage());
        }
    }

    private void saveIfValid(Station station, Session session) {
        try {
            session.save(station);
            session.getTransaction().commit();
        } catch (Exception e) {
        }
    }

    @Transactional
    public List<Station> findAllStations() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            return session.createQuery("SELECT r FROM Station r", Station.class).list();
        } catch (HibernateException e) {
            throw new DataFetchException(e.getMessage());
        }
    }

    @Transactional
    public List<Station> findAllStationsByCountry(String country) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<Station> query = session.createQuery("SELECT r FROM Station r WHERE r.country = :country", Station.class);
            query.setParameter("country", country);
            return query.list();
        } catch (HibernateException e) {
            throw new DataFetchException(e.getMessage());

        }
    }


    @Transactional
    public List<Station> findAllStationsByName(String name) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<Station> query = session.createQuery("SELECT r FROM Station r WHERE r.stationName like :name", Station.class);
            query.setParameter("name", "%" + name + "%");
            return query.list();
        } catch (HibernateException e) {
            throw new DataFetchException(e.getMessage());
        }
    }


    @Transactional
    public List<Station> findAllStationsByIdRange(String begin, String end) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<Station> query =
                    session.createNativeQuery("SELECT * FROM tbl_station AS s WHERE s.usafId BETWEEN :beginRange AND :endRange", Station.class);
            query.setParameter("beginRange", begin);
            query.setParameter("endRange", end);
            return query.list();
        } catch (HibernateException e) {
            throw new DataFetchException(e.getMessage());
        }
    }


    @Transactional
    public List<Station> findAllStationsWithinLocationRange(double latitude, double longitude, double radius) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            String queryForLocationWithinRadiusOf50 = "SELECT * FROM tbl_station WHERE (ACOS(SIN(?0) * SIN(RADIANS(CAST(latitude AS DECIMAL(65,4)))) + COS(?0) * COS(RADIANS(CAST(latitude AS DECIMAL(65,4)))) * COS(?1- RADIANS(CAST(longitude AS DECIMAL(65,4))))) * ?2) < 50";
            Query<Station> query = session.createNativeQuery(queryForLocationWithinRadiusOf50, Station.class);
            query.setParameter(0, Math.toRadians(latitude));
            query.setParameter(1, Math.toRadians(longitude));
            query.setParameter(2, radius);
            return query.list();
        } catch (HibernateException e) {
            throw new DataFetchException(e.getMessage());
        }
    }
}
