/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.service;

import com.civil.detail.ProjectsItemHistoryDetail;

import com.civil.model.ProjectsItemHistory;
import java.util.List;

/**
 *
 * @author rasel
 */
public interface ProjectsItemHistoryService extends GenericService<ProjectsItemHistory, Integer> {

    public void addList(List<ProjectsItemHistoryDetail> projectsItemDetailHistoryDetails);

    public List<ProjectsItemHistoryDetail> getAllProjectItemDetailHistoyByProjectIdAndItemId(int projectId, int itemId);

}
