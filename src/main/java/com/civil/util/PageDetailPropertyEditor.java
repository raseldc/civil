/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.util;

import com.civil.model.Page;
import com.civil.service.PageService;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author rasel
 */
public class PageDetailPropertyEditor extends PropertyEditorSupport {

    @Autowired
    public PageService pageService;

    public PageDetailPropertyEditor(PageService c) {
        this.pageService = c;
    }

    @Override
    public void setAsText(String text) {
        Page page;
        try {
            //System.out.println("in init binder TeamDetail.class... = " + text);
            Integer id;
            page = new Page();
            //   CompanyServiceImpl cmp = new CompanyServiceImpl();
            if (!text.equals("")) {
                if (text.contains(",")) {
                    text = text.substring(text.indexOf(",") + 1);
                    //System.out.println("TeamDetailPropertyEditor: text = " + text);
                }
                if (!text.equals("")) {
                    id = new Integer(text.toString());
                    page = pageService.get(id);

                    setValue(page);
                } else {
                    setValue(null);
                }
            }

        } catch (Exception e) {
            System.out.println("e : " + e.getMessage());
        }

    }
    // Converts a ComplaintReasonDetail to a String (when displaying form)

    @Override
    public String getAsText() throws IllegalArgumentException {

        Page page = (Page) this.getValue();
        //System.out.println("in getAsText..." + this.getValue());
        if (page == null) {
            return "";
        }
        return page.getId() + "";
    }
}
