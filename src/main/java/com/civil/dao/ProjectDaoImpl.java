/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.dao;

import com.civil.converter.ItemConverter;
import com.civil.converter.ProjectConverter;
import com.civil.converter.ProjectsItemConverter;
import com.civil.detail.ItemDetail;
import com.civil.detail.ProjectDetail;
import com.civil.detail.ProjectsItemDetail;
import com.civil.model.Item;
import com.civil.model.Project;
import com.civil.model.ProjectsItem;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rasel
 */
@Repository
public class ProjectDaoImpl extends GenericDaoImpl<Project, Integer> implements ProjectDao {

    @Override
    public List<ProjectDetail> getAllProjectByUser(int userId) {

        List<Project> projects = currentSession().createQuery("SELECT p FROM Project as p WHERE p.user.id =:userId").setParameter("userId", userId).list();
        return getProjectDetalListFromProjectList(projects);
    }

    private List<ProjectDetail> getProjectDetalListFromProjectList(List<Project> projects) {
        List<ProjectDetail> projectDetails = new ArrayList<>();
        projects.forEach(a -> {
            projectDetails.add(ProjectConverter.getDetail(a));
        });
        return projectDetails;
    }

    private List<ItemDetail> getItemDetalListFromItemList(List<Item> items) {
        List<ItemDetail> itemDetails = new ArrayList<>();
        items.forEach(a -> {
            itemDetails.add(ItemConverter.getDetail(a));
        });
        return itemDetails;
    }

    private List<ProjectsItemDetail> getProjectItemDetalListFromProjectsItemList(List<ProjectsItem> projectsItems) {
        List<ProjectsItemDetail> projectItemDetails = new ArrayList<>();
        projectsItems.forEach(a -> {
            Hibernate.initialize(a.getItem());
            projectItemDetails.add(ProjectsItemConverter.getDetail(a));
        });
        return projectItemDetails;
    }

//    private  <E> List<E>  setCount( List<E> listOfE,int count)
//    {
//        if(listOfE.size()>0)
//            listOfE.get(0).`(count);
//    }
    @Override
    public List<ItemDetail> getAllItemListByProject(int projectid, int skip, int take) {

        List<Item> items = currentSession().createQuery("SELECT pi.item FROM ProjectsItem pi WHERE pi.project.id =:projectId").setParameter("projectId", projectid).setFirstResult(skip).setMaxResults(take).list();
        int count_ = ((Number) currentSession().createQuery("SELECT COUNT(pi.id) FROM ProjectsItem pi WHERE pi.project.id =:projectId").setParameter("projectId", projectid).uniqueResult()).intValue();
        List<ItemDetail> itemDetails = getItemDetalListFromItemList(items);
        if (itemDetails.size() > 0) {
            itemDetails.get(0).setCount(count_);
        }
        return itemDetails;

    }

    @Override
    public List<ProjectsItemDetail> getAllPeojectItemListByProjectId(int projectid, int skip, int take) {

        List<ProjectsItem> projectsItems = currentSession().createQuery("SELECT pi FROM ProjectsItem pi WHERE pi.project.id =:projectId ORDER BY pi.item.showCode").setParameter("projectId", projectid).setFirstResult(skip).setMaxResults(take).list();
        int count_ = ((Number) currentSession().createQuery("SELECT COUNT(pi.id) FROM ProjectsItem pi WHERE pi.project.id =:projectId").setParameter("projectId", projectid).uniqueResult()).intValue();
        List<ProjectsItemDetail> itemDetails = getProjectItemDetalListFromProjectsItemList(projectsItems);
        if (itemDetails.size() > 0) {
            itemDetails.get(0).setCount(count_);
        }
        return itemDetails;

    }

}
