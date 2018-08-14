package com.org.dao;

import java.util.List;

import com.org.model.User;

/**
 * DAO interface for User Object
 * @author glodeveloper
 *
 */
public interface UserDao {
    
	/**
	 * Retrevs User Obj by emailID
	 * @param emailID
	 * @return User Obj
	 */
	User findById(String emailID);
	
	/**
	 * Saves given obj to database
	 * @param user
	 */
	void SaveUser(User user);
	
	/**
	 * Deletes User Obj for given emailID
	 * @param emailID
	 */
	void deleteUser(String emailID);
	
	/**
	 * Retirves all Users
	 */
	List<User> findallUsers();
	
}
