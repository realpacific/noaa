package com.realpacific.projectnoaa.repositories;

import com.realpacific.projectnoaa.connection.HibernateUtils;
import com.realpacific.projectnoaa.entities.Record;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class RecordRepository {
    private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    @Transactional
    public void save(List<Record> records) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        for (Record record : records) {
            session.save(record);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Transactional
    public List<Record> findAllRecords() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Record> records = session.createQuery("SELECT r FROM Record r", Record.class).list();
        session.getTransaction().commit();
        session.close();
        return records;

    }

    @Transactional
    public List<Record> findAllRecordsByCountry(String country) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query<Record> query = session.createQuery("SELECT r FROM Record r WHERE r.country = :country", Record.class);
        query.setParameter("country", country);
        List<Record> records = query.list();
        session.getTransaction().commit();
        session.close();
        return records;
    }


    @Transactional
    public List<Record> findAllRecordsByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query<Record> query = session.createQuery("SELECT r FROM Record r WHERE r.stationName like :name", Record.class);
        query.setParameter("name", "%" + name + "%");
        List<Record> records = query.list();
        session.getTransaction().commit();
        session.close();
        return records;
    }


    @Transactional
    public List<Record> findAllRecordsByIdRange(String begin, String end) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query<Record> query = session.createQuery("SELECT r FROM Record r WHERE substring(r.usafId, 1) > :begin AND substring(r.usafId, 1) < :end", Record.class);
        query.setParameter("begin", begin);
        query.setParameter("end", end);
        List<Record> records = query.list();
        session.getTransaction().commit();
        session.close();
        return records;
    }


    @Transactional
    public List<Record> findAllRecordsWithinLocationRange(double latitude, double longitude, double radius) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        String queryForLocationWithinRadiusOf50 = "SELECT * FROM tbl_record WHERE (ACOS(SIN(?0) * SIN(RADIANS(CAST(latitude AS DECIMAL(65,4)))) + COS(?0) * COS(RADIANS(CAST(latitude AS DECIMAL(65,4)))) * COS(?1- RADIANS(CAST(longitude AS DECIMAL(65,4))))) * ?2) < 50";
        Query<Record> query = session.createNativeQuery(queryForLocationWithinRadiusOf50, Record.class);
        query.setParameter(0, Math.toRadians(latitude));
        query.setParameter(1, Math.toRadians(longitude));
        query.setParameter(2, radius);
        List<Record> records = query.list();
        session.getTransaction().commit();
        session.close();
        return records;
    }
}
