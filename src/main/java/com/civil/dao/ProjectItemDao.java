/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.dao;

import com.civil.detail.ProjectsItemDetail;
import com.civil.helperClass.ProjectItemDetailHitoryDetail;
import com.civil.model.ProjectsItem;

/**
 *
 * @author rasel
 */
public interface ProjectItemDao extends GenericDao<ProjectsItem, Integer> {

    public ProjectsItemDetail getProjectItemByProjectIdAndItemId(int projectId, int itemId);
    
}
