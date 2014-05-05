package com.brightrich.service;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.brightrich.model.User;

public interface UserService {

    User findByUserName(String userName);
    void saveUser(User user);
    void deleteUser(String userName);
    List<User> findUsers(String user, MatchMode mode);
}
