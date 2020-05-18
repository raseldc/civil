/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.dao;

import com.civil.detail.PageDetail;
import com.civil.model.Page;
import java.util.List;

/**
 *
 * @author rasel
 */
public interface PageDao extends GenericDao<Page, Integer> {

    /**
     *
     * @param id
     * @return
     */
    public Page getPage(int id);

    /**
     *
     * @param skip
     * @param take
     * @param Pid
     * @return
     */
    public List<PageDetail> getAllPageDetail(int skip, int take, int Pid);

    public List<PageDetail> getAllPageDetailByPageType(int skip, int take, int pageType);

    public List<PageDetail> getPageListByRole(int roleId, int skip, int take);

    public List<PageDetail> getAllParentPage(int skip, int take, List<String> pageType);

    public List<PageDetail> getAllHeaderPageByRole(int roleId);

    public List<PageDetail> getAllSubHeaderPageByRoleAndHeader(int roleId, int headerId);

    public List<PageDetail> getAllParentPageBySubHeaderAndRole(int headerPageId, int roleId);

    public List<PageDetail> getAllPageByParentPage(int parentPageId);

    public PageDetail getMenuPageByParentPage(int parentPageId);

    public List<PageDetail> getAllPageByRoleId(int roleId);

}
