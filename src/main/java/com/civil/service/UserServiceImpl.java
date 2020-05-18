/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.service;

import com.civil.detail.PageDetail;
import com.civil.config.AuthorityInfo;
import com.civil.dao.GenericDao;
import com.civil.dao.UserDao;
import com.civil.model.Page;
import com.civil.model.User;
import com.civil.util.TreeNode;
import com.civil.viewClass.UserViewClass;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 *
 * @author rasel
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<User, Integer>
        implements UserService {

    private UserDao userDao;

    /**
     *
     * @param genericDao
     */
    @Autowired
    public UserServiceImpl(
            @Qualifier("userDaoImpl") GenericDao<User, Integer> genericDao) {

        super(genericDao);
        this.userDao = (UserDao) genericDao;

    }
    @Autowired
    PageService pageService;
    @Autowired
    RolePageService rolePageService;

    public String GetMenu_custom(Authentication authentication, String userName, String ContextPath) {
        User u = userDao.getUserByEmail(userName);
        String menu = "";
        List<Page> menuPage = userDao.getAllParentPageFroUser(u.getId());

        for (Page p : menuPage) {

            String adminHeader = "    <li class=\"treeview\">\n"
                    + "                <a href=\"#\">\n"
                    + "                    <i class=\"fa fa-list\"></i> <span>" + p.getName() + "</span>\n"
                    + "                    <span class=\"pull-right-container\">\n"
                    + "                        <i class=\"fa fa-angle-left pull-right\"></i>\n"
                    + "                    </span>\n"
                    + "                </a>   <ul class=\"treeview-menu\">";

            String roleMenu = "<li> <a href=\"" + ContextPath + "" + p.getUrl() + "\"> <i class=\"fa fa-circle-o\"></i>" + p.getName().toLowerCase() + "</a></li>\n";
            String role_menu = "  <li class=\"treeview\">\n"
                    + "                    <a href=\"#\" > "
                    + "<i class=\"fa fa-share\"></i> <span>Role</span>"
                    + " <span class=\"pull-right-container\">"
                    + "    <i class=\"fa fa-angle-left pull-right\"></i>"
                    + "</span>"
                    + "</a>\n"
                    + "                    <ul class=\"treeview-menu\">\n"
                    + roleMenu
                    + "                    </ul>\n"
                    + "                </li>";
            adminHeader = adminHeader + "" + role_menu;

            menu += adminHeader;
        }

        return menu;
    }

    /**
     *
     * @param authentication
     * @param userName
     * @param ContextPath
     * @return
     */
       public String GetMenu(String userName, String ContextPath) {
        String menu = "";
        tree_manu = new TreeNode();
         
        String agentMenu = "";
        User uf = userDao.findByUserName(userName);

        List<PageDetail> menuPageList = pageService.getPageListByRole(uf.getRole().getId(), 0, 10000);

        for (PageDetail p : menuPageList) {
            if (p.getType() == 0) {
                createTree(p, tree_manu);
            }
        }
        String tree_menu = createManu(tree_manu, ContextPath);

        return tree_menu;

    }

    /**
     *
     * @param email
     * @return
     */
    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    /**
     *
     * @param userId
     * @return
     */
    @Override
    public User getUserWithEmployeeInfo(int userId) {
        return userDao.getUserWithEmployeeInfo(userId);
    }

    /**
     *
     * set skip and take as 0 for get all user
     *
     * @param skip
     * @param take
     * @return
     */
    @Override
    public List<UserViewClass> getAllUser(int skip, int take) {
        return userDao.getAllUser(skip, take);
    }

    @Override
    public User getCNEUser() {
        return userDao.getCNEUser();
    }

    @Override
    public User getUserById(int uId) {

        return userDao.getUserById(uId);
    }

    public TreeNode tree_manu = new TreeNode();

    public TreeNode isExistTree(TreeNode treeNode, PageDetail pageDetail) {
        if (treeNode.getPageDetail().getId() == pageDetail.getId()) {
            return treeNode;
        }

        TreeNode re = null;
        for (TreeNode t : treeNode.getChild()) {
            re = isExistTree(t, pageDetail);
            if (re != null) {
                break;
            }
        }
        return re;
    }

    public TreeNode createTree(PageDetail pageDetails, TreeNode treeNode) {
        TreeNode node = isExistTree(tree_manu, pageDetails);
        if (node != null) {
            return node;
        } else {
            if (pageDetails.getPageDetail() != null) {
                TreeNode node_ = createTree(pageDetails.getPageDetail(), treeNode);
                TreeNode node_p = new TreeNode();
                node_p.setPageDetail(pageDetails);
                node_p.setName(pageDetails.getName());
                node_.getChild().add(node_p);
                return node_p;
            } else {
                TreeNode node_header = new TreeNode();
                node_header.setPageDetail(pageDetails);
                node_header.setName(pageDetails.getName());
                treeNode.getChild().add(node_header);
                return node_header;
            }

        }

    }

    private String createManu(TreeNode tree_manu, String ContextPath) {
        String adminHeader = "";
        if (tree_manu.getChild().size() > 0) {
            if (tree_manu.getName() != null) {
                adminHeader = "    <li class=\"treeview\">\n"
                        + "                <a href=\"#\">\n"
                        + "                    <i class=\"fa " + tree_manu.getPageDetail().getCssClass() + "\"></i> <span>" + tree_manu.getPageDetail().getName() + "</span>\n"
                        + "                    <span class=\"pull-right-container\">\n"
                        + "                        <i class=\"fa fa-angle-left pull-right\"></i>\n"
                        + "                    </span>\n"
                        + "                </a>   <ul class=\"treeview-menu\">";
            }
            for (TreeNode child : tree_manu.getChild()) {
                String s = createManu(child, ContextPath);
                adminHeader += s;
            }
            if (tree_manu.getName() != null) {
                adminHeader = adminHeader + " </ul>\n"
                        + "            </li>";
            }

            return adminHeader;
        } else {
            PageDetail menuPage = pageService.getMenuPageByParentPage(tree_manu.getPageDetail().getId());
            if (menuPage == null) {
                return "";
            }
            return "<li> <a href=\"" + ContextPath + "\\" + menuPage.getUrl() + "\"> <i class=\"fa " + tree_manu.getPageDetail().getCssClass() + "\"></i>" + tree_manu.getPageDetail().getName() + "</a></li>\n";
        }

    }
}
