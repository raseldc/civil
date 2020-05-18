/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.converter;

import com.civil.detail.ProjectsItemHistoryDetail;
import com.civil.model.ProjectsItemHistory;

/**
 *
 * @author rasel
 */
public class ProjectsItemHistoryConverter_ {

    public static ProjectsItemHistory getEntity(ProjectsItemHistoryDetail detail) {

        if (detail == null) {
            return null;
        }
        ProjectsItemHistory entity = new ProjectsItemHistory();
        entity.setName(detail.getName());
        entity.setId(detail.getId());
        entity.setGroup(detail.getGroup());
        entity.setFtIn(detail.getFtIn());
        entity.setInt1(detail.getIn1());
        entity.setInt2(detail.getIn2());
        entity.setInt3(detail.getIn3());
        entity.setIsAddition(detail.getIsAddition());
        entity.setTitle(detail.getTitle());
        entity.setProjectsItem(ProjectsItemConverter.getEntity(detail.getProjectsItemDetail()));

        return entity;
    }

    public static ProjectsItemHistoryDetail getDetail(ProjectsItemHistory entity) {
        if (entity == null) {
            return null;
        }
        ProjectsItemHistoryDetail detail = new ProjectsItemHistoryDetail();
        detail.setName(entity.getName());
        detail.setId(entity.getId());
        detail.setId(entity.getId());
        detail.setGroup(entity.getGroup());
        detail.setFtIn(entity.getFtIn());
        detail.setIn1(entity.getInt1());
        detail.setIn2(entity.getInt2());
        detail.setIn3(entity.getInt3());
        detail.setIsAddition(entity.getIsAddition());
        detail.setTitle(entity.getTitle());
        detail.setProjectsItemDetail(ProjectsItemConverter.getDetail(entity.getProjectsItem()));
        return detail;
    }

}
