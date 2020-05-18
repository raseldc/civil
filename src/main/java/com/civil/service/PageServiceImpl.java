/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.service;

import com.civil.detail.PageDetail;
import com.civil.dao.GenericDao;
import com.civil.dao.PageDao;
import com.civil.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author rasel
 */
@Service

public class PageServiceImpl extends GenericServiceImpl<Page, Integer>
        implements PageService {

    private PageDao pageDao;

    /**
     *
     * @param genericDao
     */
    @Autowired
    public PageServiceImpl(
            @Qualifier("pageDaoImpl") GenericDao<Page, Integer> genericDao) {

        super(genericDao);
        this.pageDao = (PageDao) genericDao;

    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public Page getPage(int id) {
        return pageDao.getPage(id);
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
        return pageDao.getAllPageDetail(skip, take, Pid);
    }

    @Override
    public List<PageDetail> getAllPageDetailByPageType(int skip, int take, int PageType) {
        return pageDao.getAllPageDetailByPageType(skip, take, PageType);
    }

    @Override
    public List<PageDetail> getPageListByRole(int roleId, int skip, int take) {
        return pageDao.getPageListByRole(roleId, skip, take);
    }

    @Override
    public List<PageDetail> getAllParentPage(int skip, int take, List<String> pageType) {
        return pageDao.getAllParentPage(skip, take, pageType);
    }

    public PageDetail getMenuPageByParentPage(int parentPageId) {
        return pageDao.getMenuPageByParentPage(parentPageId);
    }

    public List<PageDetail> getAllPageByRoleId(int roleId) {
        return pageDao.getAllPageByRoleId(roleId);
    }
}
