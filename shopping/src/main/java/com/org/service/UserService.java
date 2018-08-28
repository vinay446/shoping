package com.org.service;

import java.util.List;

import com.org.model.User;

public interface UserService {

    /**
     * Retrevs User Obj by emailID
     *
     * @param emailID
     * @return User Obj
     */
    User findById(String emailID);

    /**
     * Retrives User Obj from userID
     * @param UserID
     * @return 
     */
    User findById(int UserID);

    /**
     * Saves given obj to database
     *
     * @param user
     */
    void SaveUser(User user);

    /**
     * Deletes User Obj for given emailID
     *
     * @param emailID
     */
    void deleteUser(String emailID);

    /**
     * retrieves all user objs
     *
     * @return
     */
    List<User> findallUsers();

    /**
     * update use
     *
     * @param user
     */
    public void updateUser(User user);

    /**
     * true if emailID not exists else returns false
     *
     * @param emailID
     * @return
     */
    public boolean isUseremailIDUnique(String emailID);

}
