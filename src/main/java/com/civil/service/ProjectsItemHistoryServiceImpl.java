/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.service;

import com.civil.dao.GenericDao;
import com.civil.dao.ProjectsItemHistoryDao;
import com.civil.detail.ProjectsItemHistoryDetail;
import com.civil.model.ProjectsItemHistory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author rasel
 */
@Service

public class ProjectsItemHistoryServiceImpl extends GenericServiceImpl<ProjectsItemHistory, Integer>
        implements ProjectsItemHistoryService {

    private ProjectsItemHistoryDao projectsItemHistoryDao;

    /**
     *
     *
     * @param genericDao
     */
    @Autowired
    public ProjectsItemHistoryServiceImpl(
            @Qualifier("projectsItemHistoryDaoImpl") GenericDao<ProjectsItemHistory, Integer> genericDao) {

        super(genericDao);
        this.projectsItemHistoryDao = (ProjectsItemHistoryDao) genericDao;

    }

    @Override
    public void addList(List<ProjectsItemHistoryDetail> projectsItemHistoryDetails){
        projectsItemHistoryDao.addList(projectsItemHistoryDetails);
    }

    @Override
    public List<ProjectsItemHistoryDetail> getAllProjectItemDetailHistoyByProjectIdAndItemId(int projectId, int itemId) {
        return projectsItemHistoryDao.getAllProjectItemDetailHistoyByProjectIdAndItemId(projectId, itemId);
    }
}
