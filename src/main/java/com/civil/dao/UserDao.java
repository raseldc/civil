/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.dao;

import com.civil.detail.UserDetail;
import com.civil.model.Page;
import com.civil.model.User;
import com.civil.viewClass.UserViewClass;
import java.util.List;

/**
 *
 * @author rasel
 */
public interface UserDao extends GenericDao<User, Integer>
{

    /**
     *
     * @param username
     * @return
     */
    public User findByUserName(String username);

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
     * @param take
     * @return
     */
    public List<UserViewClass> getAllUser(int skip, int take);

    public List<UserDetail> getAllUserByRole(Integer value);

    public List<User> getAllUsersByReporterType(Integer id);

    public User getCNEUser();

    public List<Page> getAllParentPageFroUser(int id);

    public List<Page> getAllMenuPageForParentPage(int parenId);

    public User getUserById(int uId);
}
