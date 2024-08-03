package com.qoonnect.rectem_api.dao;

import com.qoonnect.rectem_api.model.User;
import com.qoonnect.rectem_api.util.TransactionUtil;
import org.hibernate.query.Query;

public class UserDao {

    public void save(User user) {
        TransactionUtil.executeTransaction(session -> {
            session.persist(user);
        });
    }

    public User findByUsername(String userName) {
        return TransactionUtil.executeTransaction(session -> {
            String hql = "FROM User WHERE username = :userName";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("userName", userName);
            return query.uniqueResult();
        });
    }

    public User findById(long id) {
        return TransactionUtil.executeTransaction(session -> {
            return session.get(User.class, id);
        });
    }
}
