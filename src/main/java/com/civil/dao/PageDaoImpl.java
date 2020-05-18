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
import com.civil.converter.PageConverter;
import com.civil.detail.PageDetail;
import com.civil.model.Page;
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
public class PageDaoImpl extends GenericDaoImpl<Page, Integer> implements PageDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Page getPage(int id) {

        Page p = find(id);
        Hibernate.initialize(p.getPage());
        return p;
    }

    /**
     *
     * @param skip
     * @param take
     * @param Pid
     * @return
     */
    @Override
    public List<PageDetail> getAllPageDetail(int skip, int take, int Pid) {

        String sql = "SELECT COUNT(p.id) FROM Page AS p";
        String sqldate = "SELECT p FROM Page AS p";
        if (Pid > 0) {
            sql = (sql + " WHERE p.page.id = " + Pid);
        }
        if (Pid > 0) {
            sqldate = (sqldate + " WHERE p.page.id = " + Pid);
        }
        sqldate = sqldate + " ORDER BY p.name";
        int count = ((Number) currentSession().createQuery(sql).uniqueResult()).intValue();

        List<Page> pages = currentSession().createQuery(sqldate).setFirstResult(skip).setMaxResults(take).list();
        List<PageDetail> details = new ArrayList<PageDetail>();
        pages.forEach(p -> {
            if (p.getPage() != null) {
                Hibernate.initialize(p.getPage());
                details.add(PageConverter.getDetail(p));
            }
        });
        //    setCount(count);
        return details;
    }

    @Override
    public List<PageDetail> getAllPageDetailByPageType(int skip, int take, int pageType) {

        String sql = "SELECT COUNT(p.id) FROM Page AS p";
        String sqldate = "SELECT p FROM Page AS p";
        if (pageType > 0) {
            sql = (sql + " WHERE p.type = " + pageType);
        }
        if (pageType > 0) {
            sqldate = (sqldate + " WHERE p.type = " + pageType);
        }
        sqldate = sqldate + " ORDER BY p.name";
        int count = ((Number) currentSession().createQuery(sql).uniqueResult()).intValue();

        List<Page> pages = currentSession().createQuery(sqldate).setFirstResult(skip).setMaxResults(take).list();
        List<PageDetail> details = new ArrayList<PageDetail>();

        pages.forEach(p -> {
            if (p.getPage() != null) {
                Hibernate.initialize(p.getPage());

            }
            details.add(PageConverter.getDetail(p));
        });
        //    setCount(count);
        return details;
    }

    @Override
    public List<PageDetail> getPageListByRole(int roleId, int skip, int take) {
        List<Page> pages = currentSession().createQuery("SELECT rp.page FROM RolePage  AS rp WHERE rp.role.id =:roleId ORDER BY rp.page.position").setParameter("roleId", roleId).setFirstResult(skip).setMaxResults(take).list();
        int count_ = ((Number) currentSession().createQuery("SELECT COUNT(rp.page.id) FROM RolePage  AS rp WHERE rp.role.id =:roleId").setParameter("roleId", roleId).uniqueResult()).intValue();;
        List<PageDetail> details = new ArrayList<PageDetail>();

        for (Page r : pages) {
            if (r.getPage() != null) {
                Hibernate.initialize(r.getPage());
                if (r.getPage().getPage() != null) {
                    Hibernate.initialize(r.getPage().getPage());
                }
            }
            details.add(PageConverter.getDetail(r));
        }
        if (details.size() > 0) {
            details.get(0).setCount(count_);
        }
        return details;
    }

    @Override
    public List<PageDetail> getAllParentPage(int skip, int take, List<String> pageType) {

        String pageTypeList = String.join(",", pageType);
        pageTypeList = "(" + pageTypeList + ")";
        List<Page> pages = currentSession().createQuery("SELECT p FROM Page  AS p WHERE p.type in " + pageTypeList + " ORDER BY p.name")
                .setFirstResult(skip).setMaxResults(take).list();
        int count_ = ((Number) currentSession().createQuery("SELECT COUNT(p.id) FROM Page  AS p WHERE p.type in " + pageTypeList).uniqueResult()).intValue();;
        List<PageDetail> details = new ArrayList<PageDetail>();
        for (Page r : pages) {
            details.add(PageConverter.getDetail(r));
        }
        if (details.size() > 0) {
            details.get(0).setCount(count_);
        }
        return details;
    }

    @Override
    public List<PageDetail> getAllHeaderPageByRole(int roleId) {

        List<PageDetail> details = new ArrayList<>();
        List<Page> pages = new ArrayList<>();
        //  List<Integer> ids = (List<Integer>) currentSession().createQuery("SELECT  p.page.id FROM Page AS p  WHERE p.type =0 GROUP BY p.page.id").list();
        List<Integer> ids = (List<Integer>) currentSession().createQuery("SELECT rp.page.page.page.id FROM RolePage AS rp  WHERE rp.page.type =0 and rp.role.id =:roleId GROUP BY rp.page.page.page.id ").setParameter("roleId", roleId).list();

        String id_st = "(";
        for (int id : ids) {
            id_st = id_st + id + ",";
        }
        id_st = id_st.substring(0, id_st.length() - 1);
        id_st = id_st + ")";
        pages = currentSession().createQuery("SELECT p FROM Page AS p WHERE p.id in " + id_st + "  ORDER BY p.position").list();
        for (Page r : pages) {
            details.add(PageConverter.getDetail(r));
        }

        return details;
    }

    @Override
    public List<PageDetail> getAllSubHeaderPageByRoleAndHeader(int roleId, int headerId) {

        List<PageDetail> details = new ArrayList<>();
        List<Page> pages = new ArrayList<>();
        //  List<Integer> ids = (List<Integer>) currentSession().createQuery("SELECT  p.page.id FROM Page AS p  WHERE p.type =0 GROUP BY p.page.id").list();
        List<Integer> ids = (List<Integer>) currentSession().createQuery("SELECT rp.page.page.id FROM RolePage AS rp  WHERE rp.page.page.page.id =:headerId AND rp.role.id =:roleId GROUP BY rp.page.page.id ").setParameter("roleId", roleId).setParameter("headerId", headerId).list();

        String id_st = "(";
        for (int id : ids) {
            id_st = id_st + id + ",";
        }
        id_st = id_st.substring(0, id_st.length() - 1);
        id_st = id_st + ")";
        pages = currentSession().createQuery("SELECT p FROM Page AS p WHERE p.id  in " + id_st + "  ORDER BY p.position").list();
        for (Page r : pages) {
            details.add(PageConverter.getDetail(r));
        }

        return details;
    }

    @Override
    public List<PageDetail> getAllParentPageBySubHeaderAndRole(int headerPageId, int roleId) {

        List<Page> pages = currentSession().createQuery("SELECT rp.page FROM RolePage AS rp WHERE rp.role.id =:roleId AND rp.page.type =:parentPageType and rp.page.page.id =:headerPageId ORDER BY rp.page.position ")
                .setParameter("roleId", roleId).setParameter("parentPageType", 0).setParameter("headerPageId", headerPageId).list();
        List<PageDetail> details = new ArrayList<>();
        for (Page p : pages) {
            Hibernate.initialize(p.getPage());
            details.add(PageConverter.getDetail(p));
        }

        return details;
    }

    @Override
    public List<PageDetail> getAllPageByParentPage(int parentPageId) {
        List<Page> pages = currentSession().createQuery("SELECT p FROM Page  AS p WHERE p.page.id =:parentPageId  ORDER BY p.name").setParameter("parentPageId", parentPageId).list();

        List<PageDetail> details = new ArrayList<PageDetail>();
        for (Page r : pages) {
            details.add(PageConverter.getDetail(r));
        }

        return details;
    }

    @Override
    public PageDetail getMenuPageByParentPage(int parentPageId) {
        Page menuPage = (Page) currentSession().createQuery("SELECT p FROM Page as p WHERE p.type = 3 AND p.page.id =:parentPageId").setParameter("parentPageId", parentPageId).uniqueResult();
        return PageConverter.getDetail(menuPage);
    }

    @Override
    public List<PageDetail> getAllPageByRoleId(int roleId) {
        List<Page> pages = new ArrayList<>();
        List<PageDetail> details = new ArrayList<>();

        List<Integer> parentPagesIds = (List<Integer>) currentSession().createQuery("SELECT rp.page.id FROM RolePage AS rp  WHERE rp.page.type = 0 and rp.role.id =:roleId  ").setParameter("roleId", roleId).list();
        if (null == parentPagesIds || parentPagesIds.isEmpty()) {

            return details;
        }
        String id_st = "(";
        for (int id : parentPagesIds) {
            id_st = id_st + id + ",";
        }
        id_st = id_st.substring(0, id_st.length() - 1);
        id_st = id_st + ")";

        pages = currentSession().createQuery("SELECT p FROM Page AS p WHERE p.page.id in " + id_st).list();
        for (Page r : pages) {
            details.add(PageConverter.getDetail(r));
        }

        return details;

    }
//    public List<PageDetail> getAllPageByRoleId(int roleId)
//    {
//        currentSession().createQuery("SELECT r FROM RolePage AS rp WHERE rp.role.id =:roleId AND rp.page.type = 0").list();
//    }
}
