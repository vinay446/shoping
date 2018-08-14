package com.org.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.dao.UserDao;
import com.org.model.User;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userdao;

	public User findById(String emailID) { 
		return userdao.findById(emailID);
	}

	public void SaveUser(User user) {
        userdao.SaveUser(user);		
	}

	public void deleteUser(String emailID) {
        userdao.deleteUser(emailID); 
	}

	public List<User> findallUsers() {
		return userdao.findallUsers();
	}
	
	
	
}
