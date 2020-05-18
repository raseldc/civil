/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.converter;

import com.civil.detail.UserDetail;
import com.civil.model.User;

/**
 *
 * @author rasel
 */
public class UserConverter {

    public static UserDetail getDetail(User entity) {
        if (entity == null) {
            return null;
        }
        UserDetail detail = new UserDetail();
        try {
            detail.setId(entity.getId());
            detail.setEmail(entity.getEmail());
            detail.setStatus(entity.getStatus());

            if (entity.getStatus() != null) {
                detail.setStatus_str(entity.getStatus() == 0 ? "Not Approved" : "Approved");
            }

            try {
                if (entity.getEmployee() != null) {
                    detail.setEmployeeDetail(EmpolyeeConverter.getDetail(entity.getEmployee()));
                }
            } catch (Exception ex) {
                System.out.println("Error " + ex.getStackTrace());
            }

            detail.setFullName(entity.getFullName() == null ? "" : entity.getFullName());

            //detail.setRole(entity.getRole() == null ? "Not Set" : entity.getRole().getName());
            detail.setRoleDetail(RoleConverter.getDetail(entity.getRole()));

            detail.setRoleId(entity.getRole() == null ? 0 : entity.getRole().getId());

        } catch (Exception ex) {
        }
        return detail;

    }

    public static User getEntity(UserDetail detail) {
        if (detail == null) {
            return null;
        }
        User entity = new User();

        entity.setId(detail.getId());
        entity.setFullName(detail.getFullName());
        entity.setEmail(detail.getEmail());
        entity.setStatus(detail.getStatus());

        if (detail.getStatus() != null) {
            entity.setStatus(detail.getStatus());
        }
        if (detail.getEmployeeDetail() != null) {
            entity.setEmployee(EmpolyeeConverter.getEntity(detail.getEmployeeDetail()));
        }

        return entity;

    }
}
