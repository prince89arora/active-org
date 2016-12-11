package org.active.model.entity;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 */
public class MySqlSessionFactory {

    private static final Logger log = Logger.getLogger(MySqlSessionFactory.class);

    private static SessionFactory sessionFactory = buildSessionFactory();

    private MySqlSessionFactory(){}

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Exception ex) {
            log.error("Error creating session :", ex);
        }
        return null;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void close() {
        sessionFactory.close();
    }

}
