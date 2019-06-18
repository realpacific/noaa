package com.realpacific.projectnoaa.connection;

import com.realpacific.projectnoaa.config.ConfigurationManager;
import com.realpacific.projectnoaa.config.ConfigurationUtils;
import com.realpacific.projectnoaa.config.NoaaConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static SessionFactory factory;
    private final static Object object = new Object();
    private final static ConfigurationManager configurationManager = ConfigurationUtils.getConfigurationManager();

    public static SessionFactory getSessionFactory() {
        if (factory == null) {
            synchronized (object) {
                if (factory == null) {
                    final Configuration configuration = new Configuration().configure();
                    overrideDefaultHibernateConfiguration(configuration);
                    factory = configuration.buildSessionFactory();
                }
            }
        }
        return factory;
    }

    private static void overrideDefaultHibernateConfiguration(Configuration configuration) {
        NoaaConfiguration noaaConfiguration = configurationManager.loadPropertyFile();
        if (noaaConfiguration != null) {
            noaaConfiguration.get(NoaaConfiguration.CONFIGURATION_URL)
                    .ifPresent(o -> configuration.setProperty("hibernate.connection.url", o.toString()));
            noaaConfiguration.get(NoaaConfiguration.CONFIGURATION_PASSWORD)
                    .ifPresent(o -> configuration.setProperty("hibernate.connection.password", o.toString()));
            noaaConfiguration.get(NoaaConfiguration.CONFIGURATION_USERNAME)
                    .ifPresent(o -> configuration.setProperty("hibernate.connection.username", o.toString()));
        }
    }

}
