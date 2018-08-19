package com.org.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.dao.UserDao;
import com.org.model.User;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userdao;

    @Override
    public User findById(String emailID) {
        return userdao.findById(emailID);
    }

    @Override
    public void SaveUser(User user) {
        userdao.SaveUser(user);
    }

    @Override
    public void deleteUser(String emailID) {
        userdao.deleteUser(emailID);
    }

    @Override
    public List<User> findallUsers() {
        return userdao.findallUsers();
    }

    @Override
    public void updateUser(User user) {
        // TODO Auto-generated method stub
        User entity = userdao.findById(user.getEmailID());
        if (entity != null) {
            entity.setCreationtime(user.getCreationtime());
            entity.setEmailID(user.getEmailID());
            entity.setExpiretime(user.getCreationtime());
            entity.setFirstname(user.getFirstname());
            entity.setId(user.getId());
            entity.setIsactive(user.getIsactive());
            entity.setLastlogin(user.getLastlogin());
            entity.setAddress(user.getAddress());
            entity.setImageid(user.getImageid());
            entity.setLastloginip(user.getLastloginip());
            entity.setLastname(user.getLastname());
            entity.setPassword(user.getPassword());
            entity.setPhone(user.getPhone());
            entity.setRoleID(user.getRoleID());
        }
    }

    @Override
    public boolean isUseremailIDUnique(String emailID) {
        // TODO Auto-generated method stub
        User user = userdao.findById(emailID);
        return user == null;
    }

}
