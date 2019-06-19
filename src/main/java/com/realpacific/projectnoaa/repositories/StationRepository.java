package com.realpacific.projectnoaa.repositories;

import com.realpacific.projectnoaa.connection.HibernateUtils;
import com.realpacific.projectnoaa.entities.Station;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StationRepository {
    private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    @Transactional
    public void save(List<Station> stations) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        for (Station station : stations) {
            session.save(station);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Transactional
    public List<Station> findAllStations() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Station> stations = session.createQuery("SELECT r FROM Station r", Station.class).list();
        session.getTransaction().commit();
        session.close();
        return stations;

    }

    @Transactional
    public List<Station> findAllStationsByCountry(String country) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query<Station> query = session.createQuery("SELECT r FROM Station r WHERE r.country = :country", Station.class);
        query.setParameter("country", country);
        List<Station> stations = query.list();
        session.getTransaction().commit();
        session.close();
        return stations;
    }


    @Transactional
    public List<Station> findAllStationsByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query<Station> query = session.createQuery("SELECT r FROM Station r WHERE r.stationName like :name", Station.class);
        query.setParameter("name", "%" + name + "%");
        List<Station> stations = query.list();
        session.getTransaction().commit();
        session.close();
        return stations;
    }


    @Transactional
    public List<Station> findAllStationsByIdRange(String begin, String end) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query<Station> query =
                session.createNativeQuery("SELECT * FROM tbl_station AS s WHERE s.usafId BETWEEN :beginRange AND :endRange", Station.class);
        query.setParameter("beginRange", begin);
        query.setParameter("endRange", end);
        List<Station> stations = query.list();
        session.getTransaction().commit();
        session.close();
        return stations;
    }


    @Transactional
    public List<Station> findAllStationsWithinLocationRange(double latitude, double longitude, double radius) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String queryForLocationWithinRadiusOf50 = "SELECT * FROM tbl_station WHERE (ACOS(SIN(?0) * SIN(RADIANS(CAST(latitude AS DECIMAL(65,4)))) + COS(?0) * COS(RADIANS(CAST(latitude AS DECIMAL(65,4)))) * COS(?1- RADIANS(CAST(longitude AS DECIMAL(65,4))))) * ?2) < 50";
        Query<Station> query = session.createNativeQuery(queryForLocationWithinRadiusOf50, Station.class);
        query.setParameter(0, Math.toRadians(latitude));
        query.setParameter(1, Math.toRadians(longitude));
        query.setParameter(2, radius);
        List<Station> stations = query.list();
        session.getTransaction().commit();
        session.close();
        return stations;
    }
}
