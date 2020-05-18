/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.util.Comparator;

import com.civil.detail.PageDetail;
import java.util.Comparator;

/**
 *
 * @author rasel
 */
public class PageDetailCustomComparator implements Comparator<PageDetail>
{

    @Override
    public int compare(PageDetail o1, PageDetail o2)
    {
        int a = o1.getId();
        int b = o2.getId();
//        if(o1.getPage()!=null)
//            a = o1.getPage().getId();
//        if(o2.getPage()!=null)
//            b = o2.getPage().getId();
        return a - b;
    }

    public static Comparator<PageDetail> NameComparator = new Comparator<PageDetail>()
    {

        public int compare(PageDetail p1, PageDetail p2)
        {
            String pageName1 = p1.getName().toUpperCase();
            String pageName2 = p2.getName().toUpperCase();

            //ascending order
            return pageName1.compareTo(pageName2);

            //descending order
            //return StudentName2.compareTo(StudentName1);
        }
    };

}
