package service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.Userdao;
import model.User;

@Service
public class UserServiceImpl implements UserService{

	private Userdao userdao;
	
	public void serUserdao(Userdao userdao) {
		this.userdao = userdao;
	}
	
	
	@Transactional
	public void addUser(User user) {
		// TODO Auto-generated method stub
		this.userdao.addUser(user);
		
	}

	@Transactional
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		this.userdao.updateUser(user);
		
	}

	@Transactional
	public List<User> listUsers() {
		// TODO Auto-generated method stub
		return this.userdao.listUsers();
	}

	@Transactional
	public User getUserbyId(int userid) {
		// TODO Auto-generated method stub
		return this.userdao.getUserbyId(userid);
	}

	@Transactional
	public void removeUser(int id) {
		// TODO Auto-generated method stub
		this.userdao.removeUser(id);
	}

	
}
