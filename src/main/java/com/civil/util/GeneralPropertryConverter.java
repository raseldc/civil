/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.util;

import com.civil.service.GenericService;

import java.beans.PropertyEditorSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rasel
 */
public class GeneralPropertryConverter<E, k> extends PropertyEditorSupport {

    
    private GenericService<E, k> gc;

    public GeneralPropertryConverter(GenericService<E, k> genericService) {
        this.gc = genericService;
    }

    @Override
    public void setAsText(String text) {
        E page;
        try {
            //System.out.println("in init binder TeamDetail.class... = " + text);
            Integer id;
            //page = new Page();
            //   CompanyServiceImpl cmp = new CompanyServiceImpl();
            if (!text.equals("")) {
                if (text.contains(",")) {
                    text = text.substring(text.indexOf(",") + 1);
                    //System.out.println("TeamDetailPropertyEditor: text = " + text);
                }
                if (!text.equals("")) {
                    id = new Integer(text.toString());
                    page = (E) gc.get((k) id);

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

        E page = (E) this.getValue();
        //System.out.println("in getAsText..." + this.getValue());
        if (page == null) {
            return "";
        }
        try {
            return page.getClass().getDeclaredField("id").toString() + "";
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(GeneralPropertryConverter.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SecurityException ex) {

            Logger.getLogger(GeneralPropertryConverter.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            return "";
        }
    }
}
