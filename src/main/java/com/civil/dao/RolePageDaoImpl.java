/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.dao;



import com.civil.model.RolePage;
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
public class RolePageDaoImpl extends GenericDaoImpl<RolePage, Integer> implements RolePageDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     *
     * @param roleId
     * @param pageId
     */
    @Override
    public void removeRoleAndPage(int roleId, int pageId) {

        List<RolePage> rpList = sessionFactory.getCurrentSession().createQuery("SELECT a FROM RolePage as a WHERE a.role.id = ? AND a.page.id = ?").setParameter(0, roleId).setParameter(1, pageId).list();
        if (rpList != null) {
            for (RolePage rp : rpList) {
                remove(rp);
            }
        }
    }

    @Override
    public RolePage getRolePageById(int Id) {
        RolePage rolePage = new RolePage();
        try {
            rolePage = (RolePage) currentSession().createQuery("SELECT u FROM RolePage AS u WHERE u.id =:Id").setParameter("Id", Id).uniqueResult();

            Hibernate.initialize(rolePage.getPage());
            Hibernate.initialize(rolePage.getRole());

            if (rolePage.getPage() != null) {
                Hibernate.initialize(rolePage.getPage().getPage());
            }

            return rolePage;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public RolePage getRolePageByRoleIdAndPageId(int roleId, int pageId) {
        return (RolePage) currentSession().createQuery("SELECT rp FROM RolePage AS rp WHERE rp.role.id =:roleId AND rp.page.id =:pageId")
                .setParameter("roleId", roleId).setParameter("pageId", pageId).uniqueResult();
    }
    
     @Override
    public void saveRolePageList(List<RolePage> rolePages) {
        try {
            int roleid = rolePages.get(0).getRole().getId();
            currentSession().createQuery("DELETE FROM RolePage AS rp WHERE rp.role.id =:roleId").
                    setParameter("roleId", roleid).executeUpdate();

            String insertQuery = "INSERT INTO role_page(\n"
                    + "	 role_id, page_id)\n"
                    + "	VALUES ";

            for (RolePage rp : rolePages) {
                insertQuery = insertQuery + "(" + rp.getRole().getId() + "," + rp.getPage().getId() + "),";
            }

            insertQuery = insertQuery.substring(0, insertQuery.length() - 1) + ";";

            currentSession().createSQLQuery(insertQuery).executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
