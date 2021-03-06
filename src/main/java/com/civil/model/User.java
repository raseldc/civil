package com.civil.model;
// Generated Dec 17, 2019 12:04:14 PM by Hibernate Tools 4.3.1

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
 * User generated by hbm2java
 */
@Entity
@Table(name = "user",
        catalog = "civil"
)
public class User implements java.io.Serializable {

    private Integer id;
    private Employee employee;
    private Role role;
    private String fullName;
    private String userName;
    private String passwordHash;
    private String passwordSalt;
    private String email;
    private String designation;
    private Integer status;
    private Integer createby;
    private Date creationDate;
    private Set<Project> projects = new HashSet<Project>(0);

    public User() {
    }

    public User(Employee employee, Role role, String fullname, String username, String passwordHash, String passwordSalt, String email, String designation, Integer status, Integer createby, Date creationDate, Set<Project> projects) {
        this.employee = employee;
        this.role = role;
        this.fullName = fullname;
        this.userName = username;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
        this.email = email;
        this.designation = designation;
        this.status = status;
        this.createby = createby;
        this.creationDate = creationDate;
        this.projects = projects;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "Id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Column(name = "fullName", length = 32)
    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "userName", length = 32)
    public String getUsername() {
        return this.userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    @Column(name = "PasswordHash", length = 500)
    public String getPasswordHash() {
        return this.passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Column(name = "PasswordSalt", length = 8)
    public String getPasswordSalt() {
        return this.passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    @Column(name = "email", length = 50)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "designation", length = 50)
    public String getDesignation() {
        return this.designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "createby")
    public Integer getCreateby() {
        return this.createby;
    }

    public void setCreateby(Integer createby) {
        this.createby = createby;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "creationDate", length = 10)
    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Project> getProjects() {
        return this.projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

}
