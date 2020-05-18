/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.converter;

import com.civil.detail.PageDetail;
import com.civil.model.Page;

/**
 *
 * @author rasel
 */
public class PageConverter {

    public static Page getEntity(PageDetail detail) {

        Page entity = new Page();
        entity.setId(detail.getId()==0?null:detail.getId());
        entity.setName(detail.getName().trim());
        entity.setNameBangla(detail.getNameBangla().trim());
        entity.setUrl(detail.getUrl().trim());
        entity.setType(detail.getType());
        entity.setPosition(0);
        entity.setCssClass(detail.getCssClass());
        return entity;
    }

    public static PageDetail getDetail(Page entity) {

        if (entity == null) {
            return null;
        }
        PageDetail detail = new PageDetail();
        detail.setId(entity.getId());
        detail.setName(entity.getName());
        detail.setNameBangla(entity.getNameBangla());
        detail.setUrl(entity.getUrl());
        detail.setType(entity.getType());
        detail.setpPageName(entity.getPage() != null ? entity.getPage().getName() : "");
        detail.setCssClass(entity.getCssClass());
        if (entity.getPage() != null) {
            detail.setPageDetail(PageConverter.getDetail(entity.getPage()));
            if (entity.getPage().getPage() != null) {
                detail.getPageDetail().setPageDetail(PageConverter.getDetail(entity.getPage().getPage()));
            }
        }
        return detail;
    }
}
