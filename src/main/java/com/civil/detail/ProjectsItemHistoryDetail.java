/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.detail;

/**
 *
 * @author rasel
 */
public class ProjectsItemHistoryDetail {

    private int id;
    private ProjectsItemDetail projectsItemDetail;
    private String name;
    private int in1;
    private int in2;
    private int in3;
    private String ftIn;
    private String title;
    private int isAddition;
    private String group;

    private int projectsItemId;

    private int projectId;
    private int itemId;

    public int getProjectsId() {
        return projectId;
    }

    public void setProjectsId(int projectsId) {
        this.projectId = projectsId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int ItemId) {
        this.itemId = ItemId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProjectsItemDetail getProjectsItemDetail() {
        return projectsItemDetail;
    }

    public void setProjectsItemDetail(ProjectsItemDetail projectsItemDetail) {
        this.projectsItemDetail = projectsItemDetail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIn1() {
        return in1;
    }

    public void setIn1(int int1) {
        this.in1 = int1;
    }

    public int getIn2() {
        return in2;
    }

    public void setIn2(int int2) {
        this.in2 = int2;
    }

    public int getIn3() {
        return in3;
    }

    public void setIn3(int int3) {
        this.in3 = int3;
    }

    public String getFtIn() {
        return ftIn;
    }

    public void setFtIn(String ftIn) {
        this.ftIn = ftIn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIsAddition() {
        return isAddition;
    }

    public void setIsAddition(int isAddition) {
        this.isAddition = isAddition;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getProjectsItemId() {
        return projectsItemId;
    }

    public void setProjectsItemId(int projectsItemId) {
        this.projectsItemId = projectsItemId;
    }

}
