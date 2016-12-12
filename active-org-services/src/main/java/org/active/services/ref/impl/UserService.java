package org.active.services.ref.impl;

import org.active.model.connection.MySqlSessionFactory;
import org.active.model.entity.User;
import org.active.services.annotations.Service;
import org.active.services.ref.PersistenceService;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 *
 */
@Service
public class UserService implements PersistenceService<User> {

    private static final Logger log = Logger.getLogger(UserService.class);

    @Override
    public void save(User user) {
        try {
            Session session = MySqlSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception ex) {
            log.error("Error saving user -> ", ex);
        }
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    public User getUserByUsername(String username) {
        try {
            Session session = MySqlSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            Query<User> query = session.createQuery("from User where username = :uname");
            query.setParameter("uname", username);
            User user = query.getSingleResult();
            return user;
        } catch (Exception ex) {
            log.error("Error getting user -> ", ex);
        }
        return null;
    }
}
