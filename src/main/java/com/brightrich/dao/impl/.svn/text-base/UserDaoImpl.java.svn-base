package com.brightrich.dao.impl;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.brightrich.dao.UserDao;
import com.brightrich.model.User;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User, String> implements UserDao {

    protected UserDaoImpl() {
        super(User.class);
    }

    public void saveUser(User user) {
        saveOrUpdate(user);
    }

    public List<User> findUsersbyName(String userName, MatchMode mode) {    	
        return findByCriteria(Restrictions.like("username", userName, mode));
    }        
    
    public User findUserById(String userId) {
        return findById(userId);
    }
	
}
