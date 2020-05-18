/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.detail;

import com.civil.model.Employee;
import com.civil.model.Role;
import java.util.Date;

/**
 *
 * @author rasel
 */
public class UserDetail {

    private Integer id;
    private EmployeeDetail employeeDetail;
    private RoleDetail roleDetail;
    private String fullName;
    private String userName;
    private String passwordHash;
    private String passwordSalt;
    private String email;
    private String designation;
    private Integer status;
    private Integer createby;
    private Date creationDate;

    private String status_str;
    private String ownPhoto_str;
    private int roleId;
    private int reporterTypeId;
    private int passwordReset;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EmployeeDetail getEmployeeDetail() {
        return employeeDetail;
    }

    public void setEmployeeDetail(EmployeeDetail employeeDetail) {
        this.employeeDetail = employeeDetail;
    }

    public RoleDetail getRoleDetail() {
        return roleDetail;
    }

    public void setRoleDetail(RoleDetail roleDetail) {
        this.roleDetail = roleDetail;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreateby() {
        return createby;
    }

    public void setCreateby(Integer createby) {
        this.createby = createby;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus_str() {
        return status_str;
    }

    public void setStatus_str(String status_str) {
        this.status_str = status_str;
    }

    public String getOwnPhoto_str() {
        return ownPhoto_str;
    }

    public void setOwnPhoto_str(String ownPhoto_str) {
        this.ownPhoto_str = ownPhoto_str;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getReporterTypeId() {
        return reporterTypeId;
    }

    public void setReporterTypeId(int reporterTypeId) {
        this.reporterTypeId = reporterTypeId;
    }

    public int getPasswordReset() {
        return passwordReset;
    }

    public void setPasswordReset(int passwordReset) {
        this.passwordReset = passwordReset;
    }

}
