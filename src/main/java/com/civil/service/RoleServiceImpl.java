/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.service;

import com.civil.detail.RoleDetail;
import com.civil.dao.GenericDao;
import com.civil.dao.RoleDao;
import com.civil.model.Role;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author rasel
 */
@Service

public class RoleServiceImpl extends GenericServiceImpl<Role, Integer>
        implements RoleService
{

    private RoleDao roleDao;

    /**
     *
     * @param genericDao
     */
    @Autowired
    public RoleServiceImpl(
            @Qualifier("roleDaoImpl") GenericDao<Role, Integer> genericDao)
    {

        super(genericDao);
        this.roleDao = (RoleDao) genericDao;

    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Role getRolePageById(int id)
    {
        return roleDao.getRolePageById(id);
    }

    @Override
    public List<RoleDetail> getAllRole(int skip, int take)
    {
        return roleDao.getAllRole(skip, take);
    }

    @Override
    public RoleDetail getRoleDetailByName(String name)
    {
        return roleDao.getRoleDetailByName(name);
    }

    @Override
    public List<RoleDetail> getAllRole() {
        return roleDao.getAllRole();
    }
}
