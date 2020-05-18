/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.converter;

import com.civil.detail.RoleDetail;
import com.civil.model.Role;

/**
 *
 * @author rasel
 */
public class RoleConverter
{

    public static Role getEntity(RoleDetail detail)
    {

        if (detail == null) {
            return null;

        }
        Role entity = new Role();
        entity.setId(detail.getId());
        entity.setName(detail.getName());
      
        return entity;
    }

    public static RoleDetail getDetail(Role entity)
    {
        if (entity == null) {
            return null;

        }
        RoleDetail detail = new RoleDetail();
        detail.setId(entity.getId());
        detail.setName(entity.getName());
        
        return detail;
    }
}
