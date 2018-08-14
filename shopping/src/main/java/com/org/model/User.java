package com.org.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userID")
	int id;
	
	@Column(name="firstname")
	String firstname;
	
	@Column(name="lastname")
	String lastname;
	
	@Id
	@Column(name="emailID")
	String emailID;
	
	@Column(name="phone")
	String phone;
	
	@Column(name="password")
	String password;
	
	@Column(name="roleID")
	String roleID;
	
	@Column(name="creationtime")
	long creationtime;
	
	@Column(name="expiretime")
	long expiretime;
	
	@Column(name="isactive")
	String isactive;
	
	@Column(name="lastlogin")
	long lastlogin;
	
	@Column(name="lastloginip")
	String lastloginip;
	
	@Column(name="lastlogindevice")
	String lastlogindevice;

	public int getId() {
		return id;
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

	public String getPassword() {
		return password;
	}

	public String getRoleID() {
		return roleID;
	}

	public long getCreationtime() {
		return creationtime;
	}

	public long getExpiretime() {
		return expiretime;
	}

	public String getIsactive() {
		return isactive;
	}

	public long getLastlogin() {
		return lastlogin;
	}

	public String getLastloginip() {
		return lastloginip;
	}

	public String getLastlogindevice() {
		return lastlogindevice;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public void setCreationtime(long creationtime) {
		this.creationtime = creationtime;
	}

	public void setExpiretime(long expiretime) {
		this.expiretime = expiretime;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public void setLastlogin(long lastlogin) {
		this.lastlogin = lastlogin;
	}

	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}

	public void setLastlogindevice(String lastlogindevice) {
		this.lastlogindevice = lastlogindevice;
	}
		
}
