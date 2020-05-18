package com.civil.model;
// Generated Jan 20, 2020 10:27:03 PM by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Project generated by hbm2java
 */
@Entity
@Table(name = "project", catalog = "civil"
)
public class Project implements java.io.Serializable {

    private Integer id;
    private User user;
    private String projectName;
    private Date startDate;
    private Date endDate;
    private String ministry;
    private String description;
    private Integer createdBy;
    private Date creationDate;
    private Date modificationDate;
    private Integer status;
    private String note;
    private Set<ProjectsItem> projectsItems = new HashSet<ProjectsItem>(0);

    public Project() {
    }
    public Project(int id) {
        this.id = id;
    }

    public Project(User user, String projectName, Date startDate, Date endDate, String ministry, String description, Integer createdBy, Date creationDate, Date modificationDate, Integer status, String note, Set<ProjectsItem> projectsItems) {
        this.user = user;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.ministry = ministry;
        this.description = description;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.status = status;
        this.note = note;
        this.projectsItems = projectsItems;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "project_name", length = 1000)
    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date", length = 10)
    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date", length = 10)
    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "ministry", length = 1000)
    public String getMinistry() {
        return this.ministry;
    }

    public void setMinistry(String ministry) {
        this.ministry = ministry;
    }

    @Column(name = "description", length = 1000)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "created_by")
    public Integer getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "creation_date", length = 10)
    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "modification_date", length = 10)
    public Date getModificationDate() {
        return this.modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "note", length = 1000)
    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
    public Set<ProjectsItem> getProjectsItems() {
        return this.projectsItems;
    }

    public void setProjectsItems(Set<ProjectsItem> projectsItems) {
        this.projectsItems = projectsItems;
    }

}