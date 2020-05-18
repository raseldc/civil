/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.util;

import com.civil.detail.PageDetail;
import java.util.Comparator;

/**
 *
 * @author rasel
 */
public class CustomComparator implements Comparator<PageDetail> {

    @Override
    public int compare(PageDetail o1, PageDetail o2) {
        int a = o1.getId();
        int b = o2.getId();
//        if(o1.getPage()!=null)
//            a = o1.getPage().getId();
//        if(o2.getPage()!=null)
//            b = o2.getPage().getId();
        return a - b;
    }
}
