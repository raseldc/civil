/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.service;

import com.civil.detail.ItemDetail;
import com.civil.detail.ProjectDetail;
import com.civil.dao.GenericDao;
import com.civil.dao.ProjectDao;
import com.civil.detail.ProjectsItemDetail;
import com.civil.model.Project;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author rasel
 */
@Service
public class ProjectServiceImpl extends GenericServiceImpl<Project, Integer>
        implements ProjectService {

    private ProjectDao projectDao;

    /**
     *
     * @param genericDao
     */
    @Autowired
    public ProjectServiceImpl(
            @Qualifier("projectDaoImpl") GenericDao<Project, Integer> genericDao) {

        super(genericDao);
        this.projectDao = (ProjectDao) genericDao;

    }

    @Override
    public List<ProjectDetail> getAllProjectByUser(int userId) {

        return projectDao.getAllProjectByUser(userId);
    }

    @Override
    public List<ItemDetail> getAllItemListByProject(int projectid, int skip, int take) {
        return projectDao.getAllItemListByProject(projectid, skip, take);
    }

    @Override
    public List<ProjectsItemDetail> getAllPeojectItemListByProjectId(int projectid, int skip, int take) {
        return projectDao.getAllPeojectItemListByProjectId(projectid, skip, take);
    }
}
