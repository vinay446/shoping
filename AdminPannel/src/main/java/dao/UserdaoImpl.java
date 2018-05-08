package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import model.User;

/**
 * @author glodeveloper
 *
 */
@Repository
public class UserdaoImpl implements Userdao{

	//for log4j
	private static final Logger logger = LoggerFactory.getLogger(UserdaoImpl.class);
	
	private SessionFactory sessionFactory ;
	
	private void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	/**
	 * adding user object to database 
	 * 
	 */
	public void addUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(user);
		logger.info("User saved Successfully ,User details "+user);
	}

	/**
	 * updating user obj with new values
	 */
	public void updateUser(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(user);
		logger.info("User updated Successfully,User details "+user);
	}

	/**
	 * list all user obj in database by select query
	 */
	public List<User> listUsers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> userlist = session.createQuery("from User").list();
		for(User u:userlist) {
			logger.info("User list "+u);
		}
		return userlist;
	}

	/***
	 * get user obj with id
	 * @param userid Userid
	 */
	public User getUserbyId(int userid) {
		Session session = this.sessionFactory.getCurrentSession();
		User u = (User) session.load(User.class,userid);
		logger.info("User loaded Successfully "+u);
		return u;
	}
     
	/***
	 * remove user obj from database
	 */
	public void removeUser(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		User u = (User) session.load(User.class, id);
		if(u!=null) {
			session.delete(u);
			logger.info("Person deleted successfully "+u);
		}
	}

}
