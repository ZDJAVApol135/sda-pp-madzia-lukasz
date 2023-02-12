package com.sda.dao;

import com.sda.db.HibernateUtils;
import com.sda.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UsersDAO {


    public void create(User user) {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();

        session.persist(user);

        transaction.commit();
        session.close();
    }

    public boolean delete(String username) {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();

        User user = session.find(User.class, username);
        boolean exists = user != null;
        if (exists) {
            session.remove(user);
        }

        transaction.commit();
        session.close();
        return exists;
    }

    public List<User> findAll() {
        try (Session session = HibernateUtils.openSession()) {
            List<User> users = session.createQuery("From User", User.class).list();
            return users;
        }
    }

    public User findByUsername(String username) {
        try (Session session = HibernateUtils.openSession()) {
            return session.createQuery("from User where username = :username", User.class)
                    .setParameter("username", username)
                    .uniqueResult();
        }
    }

    public User update(User user) {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();

        User updatedUser = session.merge(user);

        transaction.commit();
        session.close();

        return updatedUser;
    }

    public boolean exists(String username){
        try (Session session = HibernateUtils.openSession()){
            String query ="SELECT count(u) FROM User u WHERE u.username= :username";
            Long usersCount = session.createQuery(query, Long.class)
                    .setParameter("username", username)
                    .uniqueResult();

            return usersCount > 0;

        }
    }
}

