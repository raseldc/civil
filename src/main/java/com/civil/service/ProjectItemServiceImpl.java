/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.service;

import com.civil.detail.ProjectsItemDetail;
import com.civil.dao.GenericDao;
import com.civil.dao.ProjectItemDao;
import com.civil.helperClass.ProjectItemDetailHitoryDetail;
import com.civil.model.ProjectsItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author rasel
 */
@Service
public class ProjectItemServiceImpl extends GenericServiceImpl<ProjectsItem, Integer>
        implements ProjectItemService {

    private ProjectItemDao projectItemDao;

    /**
     *
     *
     * @param genericDao
     */
    @Autowired
    public ProjectItemServiceImpl(
            @Qualifier("projectItemDaoImpl") GenericDao<ProjectsItem, Integer> genericDao) {

        super(genericDao);
        this.projectItemDao = (ProjectItemDao) genericDao;

    }

    @Override
    public ProjectsItemDetail getProjectItemByProjectIdAndItemId(int projectId, int itemId) {
        return projectItemDao.getProjectItemByProjectIdAndItemId(projectId, itemId);
    }

}
