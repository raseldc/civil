/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.service;

import com.civil.dao.GenericDao;
import com.civil.dao.RolePageDao;
import com.civil.model.RolePage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author rasel
 */
@Service
public class RolePageServiceImpl extends GenericServiceImpl<RolePage, Integer>
        implements RolePageService {

    private RolePageDao rolePageDao;

    /**
     *
     * @param genericDao
     */
    @Autowired
    public RolePageServiceImpl(
            @Qualifier("rolePageDaoImpl") GenericDao<RolePage, Integer> genericDao) {

        super(genericDao);
        this.rolePageDao = (RolePageDao) genericDao;

    }

    /**
     *
     * @param roleId
     * @param pageId
     */
    @Override
    public void removeRoleAndPage(int roleId, int pageId) {
        rolePageDao.removeRoleAndPage(roleId, pageId);
    }

    @Override
    public RolePage getRolePageById(int id) {
        return rolePageDao.getRolePageById(id);
    }

    @Override
    public RolePage getRolePageByRoleIdAndPageId(int roleId, int pageId) {
        return rolePageDao.getRolePageByRoleIdAndPageId(roleId, pageId);
    }

    @Override
    public void saveRolePageList(List<RolePage> rolePages) {
        rolePageDao.saveRolePageList(rolePages);
    }
}
