package com.brightrich.service.impl;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;    
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brightrich.dao.UserDao;
import com.brightrich.model.User;

@Service("userDetailsService") 
public class UserDetailsServiceImpl implements UserDetailsService, AuthenticationUserDetailsService<OpenIDAuthenticationToken> {	    
	
  @Autowired private UserDao userDao;
  @Autowired private Assembler assembler;

  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException, DataAccessException {

	System.out.println("searching for user : " + username);  
    
    List<User> userList = userDao.findUsersbyName(username, MatchMode.EXACT);            
    
    
    if (userList.size() < 1)
      throw new UsernameNotFoundException("user not found");
    
    User userEntity = userList.get(0);
    System.out.println("User Name = " + userEntity.getUsername());
    //System.out.println("User found = " + userEntity.toString());
    
    return assembler.buildUserFromUserEntity(userEntity);
  }
  
  @Transactional(readOnly = true)
	public UserDetails loadUserDetails(OpenIDAuthenticationToken token)
			throws UsernameNotFoundException {
		String email = getEmail(token);
	    return loadUserByUsername(email);
	}
	
	private String getEmail(OpenIDAuthenticationToken token) {
	    for (OpenIDAttribute attribute : token.getAttributes()) {
	        if (attribute.getName().equals("email")) {
	            return attribute.getValues().get(0);
	        }
	    }
	    return null;
	}
}