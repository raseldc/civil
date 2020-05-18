/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.service;

import com.civil.detail.ItemDetail;
import com.civil.detail.ProjectDetail;
import com.civil.detail.ProjectsItemDetail;
import com.civil.model.Project;
import java.util.List;

/**
 *
 * @author rasel
 */
public interface ProjectService extends GenericService<Project, Integer> {

    public List<ProjectDetail> getAllProjectByUser(int userId);

    public List<ItemDetail> getAllItemListByProject(int projectid, int skip, int take);

    public List<ProjectsItemDetail> getAllPeojectItemListByProjectId(int projectid, int skip, int take);
}
