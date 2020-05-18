/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.service;

import com.civil.detail.RoleDetail;
import com.civil.model.Role;
import java.util.List;

/**
 *
 * @author rasel
 */
public interface RoleService extends GenericService<Role, Integer> {

    /**
     *
     * @param id
     * @return
     */
 public Role getRolePageById(int id);

 public List<RoleDetail> getAllRole(int skip, int take);
 public List<RoleDetail> getAllRole();
 public RoleDetail getRoleDetailByName(String name);
}
