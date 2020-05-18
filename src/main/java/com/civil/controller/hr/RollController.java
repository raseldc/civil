/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.controller.hr;

import com.civil.converter.RoleConverter;
import com.civil.detail.PageDetail;
import com.civil.detail.RoleDetail;
import com.civil.model.Page;
import com.civil.model.Role;
import com.civil.model.RolePage;
import com.civil.service.PageService;
import com.civil.service.RolePageService;
import com.civil.service.RoleService;
import com.civil.util.JsonResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author rasel
 */
@Controller
@RequestMapping(value = {"/role/"})
public class RollController {

    @Autowired
    RoleService roleService;
    @Autowired
    PageService pageService;

    @Autowired
    RolePageService rolePageService;

    public ModelAndView generalModelandView(ModelAndView modelAndView, String msg) {

        modelAndView.addObject("action", "role/roleform");
        modelAndView.addObject("model", new RoleDetail());
        modelAndView.addObject("msg", msg);
        modelAndView.setViewName("/hr/role/roleForm");
        return modelAndView;
    }

    @RequestMapping(value = {"roleform"}, method = RequestMethod.GET)
    public ModelAndView roleForm() {
        System.out.println("User Reg");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView = generalModelandView(modelAndView, "");
        return modelAndView;
    }

    @RequestMapping(value = {"roleform"}, method = RequestMethod.POST)
//    @RolesAllowed({"ROLE_ADMIN", "ROLE_ADD"})
    public ModelAndView roleAdd(@Valid RoleDetail rollDetail, BindingResult result, Model model, HttpServletRequest request) {

//        User uf = new User();
//        uf.setUserName("raseldc");
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        uf.setPasswordHash(passwordEncoder.encode("123456"));
//        uf.setPin("123456");
//        uf.setIsDeleted(true);
//        uf.setAuthType(1);
//
//        mixService.add(uf);
        //     roleService.getRolePageById(1);
        System.out.println("Role  Add");
        //    List<Role> rolelist = roleService.getAll();
        Role role = RoleConverter.getEntity(rollDetail);
        if (role.getId() != 0) {
            Role roleDb = roleService.get(role.getId());
            roleDb.setName(role.getName());
            roleService.update(roleDb);
        } else {
            role.setCreationDate(new Date());

            role.setId(null);
            roleService.add(role);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView = generalModelandView(modelAndView, "Submit succesfully");

        return modelAndView;
    }

    @RequestMapping(value = "roleall")
    @ResponseBody
    public String getAllRole(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {

        String startIndexSt = request.getParameter("start");
        int startPageIndex = startIndexSt != null ? Integer.parseInt(startIndexSt) : 0;
        String recordsPerPageSt = request.getParameter("length");
        int recordsPerPage = recordsPerPageSt != null ? Integer.parseInt(recordsPerPageSt) : Integer.MAX_VALUE;
        int draw = Integer.parseInt(request.getParameter("draw"));

        List<RoleDetail> roleDetailList = roleService.getAllRole(startPageIndex, recordsPerPage);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonArray = gson.toJson(roleDetailList);

        jsonArray = "{ \"draw\": " + draw + ",\"recordsTotal\": " + roleDetailList.size() + ",\"recordsFiltered\": " + roleDetailList.get(0).getCount() + ",\"data\":" + jsonArray + "}";

        System.out.println("Ajax " + jsonArray);
        return jsonArray;
    }

    @RequestMapping(value = {"rolepage"}, method = RequestMethod.GET)
    public ModelAndView rolePage() {
        System.out.println("User Reg");
        ModelAndView modelAndView = new ModelAndView();
        // modelAndView = generalModelandView(modelAndView, "");

        List<RoleDetail> roleDetailList = roleService.getAllRole(0, Integer.MAX_VALUE);

        modelAndView.addObject("roleList", roleDetailList);

        //     List<Page> pageList = pageService.getAll();
        List<String> pageType = new ArrayList<>();
        pageType.add("0");
        List<PageDetail> pageDetailList = pageService.getAllParentPage(0, Integer.MAX_VALUE, pageType);

        modelAndView.addObject("pageList", pageDetailList);
        modelAndView.addObject("roleList__", "asdfasf");
        modelAndView.setViewName("/hr/role/role_page");
        return modelAndView;
    }

    @RequestMapping(value = "pagebyrole")
    @ResponseBody
    public String getAllPageByRole(@RequestParam(value = "rId", required = false) String rId, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {

        String startIndexSt = request.getParameter("start");
        int startPageIndex = startIndexSt != null ? Integer.parseInt(startIndexSt) : 0;
        String recordsPerPageSt = request.getParameter("length");
        int recordsPerPage = recordsPerPageSt != null ? Integer.parseInt(recordsPerPageSt) : Integer.MAX_VALUE;
        int draw = Integer.parseInt(request.getParameter("draw"));

        int rid = Integer.parseInt(rId);

        List<PageDetail> pageDetailList = pageService.getPageListByRole(rid, startPageIndex, recordsPerPage);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonArray = gson.toJson(pageDetailList);

        jsonArray = "{ \"draw\": " + draw + ",\"recordsTotal\": " + pageDetailList.size() + ",\"recordsFiltered\": " + pageDetailList.get(0).getCount() + ",\"data\":" + jsonArray + "}";

        System.out.println("Ajax " + jsonArray);
        return jsonArray;
    }

    @RequestMapping(value = "pagebyrolesubmuit")
    @ResponseBody
    public String pageByRoleSubmuit(@RequestParam(value = "rId", required = false) String rId, @RequestParam(value = "pId", required = false) String pId, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {

        int roleId = Integer.parseInt(rId);
        int pageId = Integer.parseInt(pId);

        Page page = pageService.get(pageId);
        Role role = roleService.get(roleId);
        List<Page> pages = pageService.getAll();
        List<Page> pages_ = new ArrayList<Page>();

        RolePage rp = rolePageService.getRolePageByRoleIdAndPageId(roleId, pageId);
        if (rp == null) {
            rp = new RolePage();
            rp.setPage(page);
            rp.setRole(role);
            rolePageService.add(rp);
        }

        JsonResult jr = new JsonResult(false, "Rol Page Add successfully");
        return jr.toJsonString();
    }

    @RequestMapping(value = "pagebyroledelete")
    @ResponseBody
    public String pageByRoleDelete(@RequestParam(value = "rId", required = false) String rId, @RequestParam(value = "pId", required = false) String pId, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {

        int roleId = Integer.parseInt(rId);
        int pageId = Integer.parseInt(pId);

        List<Page> pages = pageService.getAll();
        List<Page> pages_ = new ArrayList<Page>();
        Role r = roleService.get(roleId);

        RolePage rp = rolePageService.getRolePageByRoleIdAndPageId(roleId, pageId);
        if (rp != null) {
            rolePageService.remove(rp);
        }
        JsonResult jr = new JsonResult(false, "Rol Page Delete successfully");
        return jr.toJsonString();
    }
    
    @RequestMapping(value = "getpagebyrole")
    @ResponseBody
    public String getpagebyrole(@RequestParam(value = "rId", required = false) String rId, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {

        int rid = Integer.parseInt(rId);

        List<PageDetail> pageDetailList = pageService.getPageListByRole(rid, 0, Integer.MAX_VALUE);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonArray = gson.toJson(pageDetailList);

        return jsonArray;
    }
    
       @RequestMapping(value = "savepagesforrole")
    @ResponseBody
    public String savePagesForRole(@RequestParam(value = "rId", required = false) String rId, @RequestParam(value = "pagesId", required = false) String pagesId, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {

        int roleId = Integer.parseInt(rId);
        List<String> pagesIdList = Arrays.asList(pagesId.split(" "));
        List<RolePage> rpList = new ArrayList<>();
        Role role = new Role(roleId);
        pagesIdList.forEach(id_st -> {
            int id = Integer.parseInt(id_st);
            Page page = new Page(id);
            rpList.add(new RolePage(page, role));
        });
        rolePageService.saveRolePageList(rpList);

        JsonResult jr = new JsonResult(false, "Rol Page Add successfully");
        return jr.toJsonString();
    }
}
