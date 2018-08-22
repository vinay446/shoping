package com.org.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.org.model.User;

@Repository("UserDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory factory;

    protected Session getSession() {
        return factory.getCurrentSession();
    }

    @Override
    public User findById(String emailID) {
        return (User) getSession().get(User.class, emailID);
    }

    @Override
    public void SaveUser(User user) {
        getSession().saveOrUpdate(user);

    }

    @Override
    public void deleteUser(String emailID) {
        Query query = getSession().createSQLQuery("delete from User where emailID = :emailID");
        query.setString("emailID", emailID);
        query.executeUpdate();
    }

    @Override
    public List<User> findallUsers() {
        return (List<User>) getSession().createCriteria(User.class).list();
    }

}
