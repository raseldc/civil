/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.util;



import com.civil.detail.PageDetail;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rasel
 */
public class TreeNode {
    
    String name;    
    PageDetail pageDetail;
    List<TreeNode> child;

    public TreeNode() {
        child = new ArrayList<>();
        pageDetail = new PageDetail();
    }
    
    

    public PageDetail getPageDetail() {
        return pageDetail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPageDetail(PageDetail pageDetail) {
        this.pageDetail = pageDetail;
    }

    public List<TreeNode> getChild() {
        return child;
    }

    public void setChild(List<TreeNode> child) {
        this.child = child;
    }
    
    
    
}
