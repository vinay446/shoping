package dao;

import java.util.List;

import model.User;

public interface Userdao {
   
	public void addUser(User user);
	
	public void updateUser(User user);
	
	public List<User> listUsers();
	
	public User getUserbyId(int userid);
	
	public void removeUser(int id);
}
