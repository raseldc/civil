/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.dao;

/**
 *
 * @author rasel
 */


import com.civil.converter.RoleConverter;
import com.civil.detail.RoleDetail;
import com.civil.model.Role;
import com.civil.model.RolePage;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rasel
 */
@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role, Integer> implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Role getRolePageById(int id) {

        Role r = find(id);
        Hibernate.initialize(r.getRolePages());
        for (RolePage rp : r.getRolePages()) {
            Hibernate.initialize(rp.getPage());
        }
        return r;
    }

    @Override
    public List<RoleDetail> getAllRole(int skip, int take) {
        
        List<Role> roles = currentSession().createQuery("SELECT r FROM Role AS r  ORDER BY r.name").setFirstResult(skip).setMaxResults(take).list();
        int count_ = ((Number) currentSession().createQuery("SELECT COUNT(r.id) FROM Role AS r").uniqueResult()).intValue();
        List<RoleDetail> details = new ArrayList<RoleDetail>();
        for (Role r : roles) {
            details.add(RoleConverter.getDetail(r));
        }
        if (details.size() > 0) {
            details.get(0).setCount(count_);
        }
        return details;

    }

    @Override
    public RoleDetail getRoleDetailByName(String name) {
        Role r = (Role) currentSession().createQuery("SELECT r FROM Role AS r WHERE LOWER(r.name) = :name ").setParameter("name", name.trim().toLowerCase()).uniqueResult();
        return RoleConverter.getDetail(r);
    }

    @Override
    public List<RoleDetail> getAllRole() {
        List<Role> roles = currentSession().createQuery("SELECT r FROM Role AS r  ORDER BY r.name ").list();
        List<RoleDetail> details = new ArrayList<RoleDetail>();
        for (Role r : roles) {
            details.add(RoleConverter.getDetail(r));
        }
        return details;
    }

}
