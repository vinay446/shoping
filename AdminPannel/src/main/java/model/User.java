package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 */

@Entity
@Table(name="users")

public class User {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userID;
	
	private String firstname,lastname,emailID,phone,roleID,isactive,lastloginip,lastlogindevice;
	
	private long creationtime,expiretime,lastlogin;

	public int getUserID() {
		return userID;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getEmailID() {
		return emailID;
	}

	public String getPhone() {
		return phone;
	}

	public String getRoleID() {
		return roleID;
	}

	public String getIsactive() {
		return isactive;
	}

	public String getLastloginip() {
		return lastloginip;
	}

	public String getLastlogindevice() {
		return lastlogindevice;
	}

	public long getCreationtime() {
		return creationtime;
	}

	public long getExpiretime() {
		return expiretime;
	}

	public long getLastlogin() {
		return lastlogin;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}

	public void setLastlogindevice(String lastlogindevice) {
		this.lastlogindevice = lastlogindevice;
	}

	public void setCreationtime(long creationtime) {
		this.creationtime = creationtime;
	}

	public void setExpiretime(long expiretime) {
		this.expiretime = expiretime;
	}

	public void setLastlogin(long lastlogin) {
		this.lastlogin = lastlogin;
	}
}
