/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.service;

import com.civil.model.RolePage;
import java.util.List;

/**
 *
 * @author rasel
 */
public interface RolePageService extends GenericService<RolePage, Integer> {

    /**
     *
     * @param roleId
     * @param pageId
     */
    public void removeRoleAndPage(int roleId, int pageId);  
        
    public RolePage getRolePageByRoleIdAndPageId(int roleId, int pageId);
    public RolePage getRolePageById(int id);
    
    public void saveRolePageList(List<RolePage> rolePages);

    
}
