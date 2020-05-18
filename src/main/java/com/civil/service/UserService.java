/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.service;

import com.civil.detail.UserDetail;
import com.civil.model.User;
import com.civil.viewClass.UserViewClass;

import java.util.List;

import org.springframework.security.core.Authentication;

/**
 *
 * @author rasel
 */
public interface UserService extends GenericService<User, Integer> {

    /**
     *
     * @param authentication
     * @param userName
     * @param ContextPath
     * @return
     */
    public String GetMenu(String userName, String ContextPath);

    /**
     *
     * @param email
     * @return
     */
    public User getUserByEmail(String email);

    /**
     *
     * @param userId
     * @return
     */
    public User getUserWithEmployeeInfo(int userId);

    /**
     *
     * @param skip
     * @param take set skip and take as 0 fro get all user in DB
     * @return List of user
     */
    public List<UserViewClass> getAllUser(int skip, int take);

    public User getCNEUser();

    public User getUserById(int parseInt);
}
