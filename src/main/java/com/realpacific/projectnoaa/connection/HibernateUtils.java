package com.realpacific.projectnoaa.connection;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {
    private static SessionFactory factory;
    private final static Object object = new Object();

    public static SessionFactory getSessionFactory() {
        if (factory == null) {
            synchronized (object) {
                if (factory == null) {
                    StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
                    MetadataSources sources = new MetadataSources(registry);
                    Metadata metadata = sources.getMetadataBuilder().build();

                    factory = metadata.getSessionFactoryBuilder().build();
                }
            }
        }
        return factory;
    }

}
