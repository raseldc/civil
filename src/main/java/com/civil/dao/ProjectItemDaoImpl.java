/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.dao;

import com.civil.converter.ProjectsItemConverter;
import com.civil.detail.ProjectsItemDetail;
import com.civil.helperClass.ProjectItemDetailHitoryDetail;
import com.civil.model.ProjectsItem;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rasel
 */
@Repository
public class ProjectItemDaoImpl extends GenericDaoImpl<ProjectsItem, Integer> implements ProjectItemDao {

    
    @Override
    public ProjectsItemDetail getProjectItemByProjectIdAndItemId(int projectId, int itemId) {
        try {
            ProjectsItem projectsItem = (ProjectsItem) currentSession().createQuery("SELECT pi FROM ProjectsItem AS pi WHERE pi.item.id =:itemId AND pi.project.id =:projectId").setParameter("projectId", projectId).setParameter("itemId", itemId).uniqueResult();
            return ProjectsItemConverter.getDetail(projectsItem);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ex :");
            return null;
        }
    }

}
