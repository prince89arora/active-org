package org.active.services.ref.impl;

import org.active.model.entity.MySqlSessionFactory;
import org.active.model.entity.User;
import org.active.services.annotations.Service;
import org.active.services.ref.PersistenceService;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
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
    public List<User> get(Query query) {
     return null;
    }
}
