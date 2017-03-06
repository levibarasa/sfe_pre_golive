package com.orig.gls.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.c3p0.internal.C3P0ConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.internal.SessionFactoryImpl;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    //to disallow creating objects by other classes.
    private HibernateUtil() {
    }
//making the Hibernate SessionFactory object as singleton

    public static synchronized SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }

    public static void closeSession(Session session) {
        try {
            if (session.isOpen()) {
                session.close();
            }
            if (serviceRegistry != null) {
                closeSessionFactory(sessionFactory);
                StandardServiceRegistryBuilder.destroy(serviceRegistry);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private static void closeSessionFactory(SessionFactory factory) {
        if (factory instanceof SessionFactoryImpl) {
            SessionFactoryImpl sf = (SessionFactoryImpl) factory;
            ConnectionProvider conn = sf.getConnectionProvider();
            if (conn instanceof C3P0ConnectionProvider) {
                ((C3P0ConnectionProvider) conn).close();
            }
        }
        factory.close();
    }
}
