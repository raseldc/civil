/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.controllers;

import com.civil.converter.ProjectConverter;
import com.civil.converter.ProjectsItemConverter;
import com.civil.detail.ItemDetail;
import com.civil.detail.ProjectDetail;
import com.civil.detail.ProjectsItemDetail;
import com.civil.detail.ProjectsItemHistoryDetail;
import com.civil.model.Item;
import com.civil.model.Project;
import com.civil.model.ProjectsItem;
import com.civil.model.ProjectsItemHistory;
import com.civil.model.User;
import com.civil.service.ItemService;
import com.civil.service.ProjectItemService;
import com.civil.service.ProjectService;
import com.civil.service.ProjectsItemHistoryService;
import com.civil.util.JsonResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping(value = {"/project/"})
public class ProjectController {

    @Autowired
    ProjectService projectService;
    @Autowired
    ItemService itemService;

    @Autowired
    ProjectItemService projectItemService;

    @Autowired
    ProjectsItemHistoryService projectsItemDetailHistoryService;

    // <editor-fold defaultstate="collapsed" desc="Project Create">
    @RequestMapping(value = {"/projectcreate"}, method = RequestMethod.GET)
    public ModelAndView projectCreate() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/project/projectCreate");
        return modelAndView;
    }

    @RequestMapping(value = {"/projectaddedit"}, method = RequestMethod.POST)
    @ResponseBody
    public String projectsave(@RequestBody ProjectDetail detail, HttpServletRequest request, HttpSession session) {

        JsonResult jr = new JsonResult(false, "save");
        try {
            Project project = ProjectConverter.getEntity(detail);
            if (project.getId() == null || project.getId() == 0) {
                project.setId(null);
                User u = new User();
                u.setId(1);
                project.setUser(u);
                projectService.add(project);
            } else {
                Project projectFromDb = projectService.get(project.getId());
                projectFromDb.setProjectName(project.getProjectName());
                projectFromDb.setStartDate(project.getStartDate());
                projectFromDb.setEndDate(project.getEndDate());

//                User u = new User();
//                u.setId(1);
//                projectFromDb.setUser(u);
                projectService.update(projectFromDb);
            }
        } catch (Exception ex) {
            System.out.println("Ex" + ex.getMessage());
            ex.printStackTrace();
            jr = new JsonResult(true, ex.getMessage());
            ex.getStackTrace();
        }
        return jr.toJsonString();

    }

    @RequestMapping(value = "/getallproject")
    @ResponseBody
    public String getAllPage(HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {

        String startIndexSt = request.getParameter("start");
        int startPageIndex = startIndexSt != null ? Integer.parseInt(startIndexSt) : 0;
        String recordsPerPageSt = request.getParameter("length");
        int recordsPerPage = recordsPerPageSt != null ? Integer.parseInt(recordsPerPageSt) : Integer.MAX_VALUE;
        //     int draw = Integer.parseInt(request.getParameter("draw"));
        User logInUser = (User) httpSession.getAttribute("User");
        List<ProjectDetail> projectDetails = projectService.getAllProjectByUser(logInUser.getId());

        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").setPrettyPrinting().create();
        String jsonArray = gson.toJson(projectDetails);

        jsonArray = "{ \"draw\": " + 0 + ",\"recordsTotal\": " + projectDetails.size() + ",\"recordsFiltered\": " + projectDetails.size() + ",\"data\":" + jsonArray + "}";

        System.out.println("Ajax " + jsonArray);
        return jsonArray;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="ProjectDeatil Add">
    @RequestMapping(value = {"/projectdeatilcreate"}, method = RequestMethod.GET)
    public ModelAndView projectDeatilCreate() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/project/projectDetailCreate");
        return modelAndView;
    }

    @RequestMapping(value = "/getitemlistforthisproject")
    @ResponseBody
    public String getItemListForThisProject(@RequestParam("projectid") int projectid, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {

        String startIndexSt = request.getParameter("start");
        int startPageIndex = startIndexSt != null ? Integer.parseInt(startIndexSt) : 0;
        String recordsPerPageSt = request.getParameter("length");
        int recordsPerPage = recordsPerPageSt != null ? Integer.parseInt(recordsPerPageSt) : Integer.MAX_VALUE;
        //     int draw = Integer.parseInt(request.getParameter("draw"));
        User logInUser = (User) httpSession.getAttribute("User");
        //  List<ItemDetail> itemDetailsForAProject = projectService.getAllItemListByProject(projectid, startPageIndex, recordsPerPage);

        List<ProjectsItemDetail> projectsItemDetails = projectService.getAllPeojectItemListByProjectId(projectid, startPageIndex, recordsPerPage);
        
        String previousCode = "";
        for(ProjectsItemDetail pi : projectsItemDetails)
        {
            if(previousCode.equals(""))
            {
                previousCode = pi.getItemDetail().getShowCode();
                continue;
            }
            if(previousCode.equals(pi.getItemDetail().getShowCode()))
            {
                pi.getItemDetail().setParentItemDescription(pi.getItemDetail().getDescription());
            }
            previousCode = pi.getItemDetail().getShowCode();
        }
        //   List<ProjectDetail> projectDetails = projectService.getAllProjectByUser(logInUser.getId());

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create();
        String jsonArray = gson.toJson(projectsItemDetails);

        jsonArray = "{ \"draw\": " + 0 + ",\"recordsTotal\": " + projectsItemDetails.size() + ",\"recordsFiltered\": " + projectsItemDetails.size() + ",\"data\":" + jsonArray + "}";

        System.out.println("Ajax " + jsonArray);
        return jsonArray;
    }

    @RequestMapping(value = "/projectdetailadd", method = RequestMethod.POST)
    @ResponseBody
    public String projectdetailadd(@RequestBody ProjectsItemDetail projectsItemDetail,
            HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {

        List<String> itemIdList = Arrays.asList(projectsItemDetail.getItemList().split(","));
        JsonResult jr = new JsonResult(false, "Inser");
        
        for (String iremId : itemIdList) {
            projectsItemDetail.setItemId(Integer.parseInt(iremId));

            ProjectsItem projectsItem = new ProjectsItem();

            Project project = new Project(projectsItemDetail.getProjectId());            

            Item item = new Item();
            int item_int = projectsItemDetail.getItemId();
            item.setId(item_int);

            Item itemFromDb = itemService.get(item_int);
         //   float totalPrice = projectsItem.getQuantity() * itemFromDb.getPrice();
            ProjectsItemDetail projectItemDetail = projectItemService.getProjectItemByProjectIdAndItemId(project.getId(), item_int);

            if (projectItemDetail == null) {
                projectsItem.setProject(project);
                projectsItem.setItem(item);
                projectItemService.add(projectsItem);
            } else {
                jr = new JsonResult(true, "Already Exist");
            }
        }
        return jr.toJsonString();
    }

    @RequestMapping(value = "/project-item-detail-history", method = RequestMethod.POST)
    @ResponseBody
    public String projectitemdetailhistory(@RequestBody List<ProjectsItemHistoryDetail> projectsItemDetailHistoryDetails,
            HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {

//        ProjectsItem projectItem = null;
//        for (ProjectsItemHistoryDetail projectsItemDetailHistoryDetail : projectsItemDetailHistoryDetails) {
//            if (projectItem == null) {
//                ProjectsItemDetail projectsItemDetail = projectItemService.getProjectItemByProjectIdAndItemId(projectsItemDetailHistoryDetail.getProjectId(), projectsItemDetailHistoryDetail.getItemId());
//                projectItem = ProjectsItemConverter.getEntity(projectsItemDetail);
//            }
//            ProjectsItemHistory projectsItemDetailHistory = ProjectsItemDetailHistoryConverter.getEntity(projectsItemDetailHistoryDetail);
//            projectsItemDetailHistory.setProjectsItem(projectItem);
//           // projectsItemDetailHistoryService.add(projectsItemDetailHistory);
//        }
        projectsItemDetailHistoryService.addList(projectsItemDetailHistoryDetails);

        JsonResult jr = new JsonResult(false, "Insert Successfully");

        return jr.toJsonString();
    }

    @RequestMapping(value = "/getall-project-item-detail-history", method = RequestMethod.POST)
    @ResponseBody
    public String getallProjectItemDetailHistory(@RequestBody ProjectsItemDetail projectsItemDetail,
            HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {

//        ProjectsItem projectItem = null;
//        for (ProjectsItemHistoryDetail projectsItemDetailHistoryDetail : projectsItemDetailHistoryDetails) {
//            if (projectItem == null) {
//                ProjectsItemDetail projectsItemDetail = projectItemService.getProjectItemByProjectIdAndItemId(projectsItemDetailHistoryDetail.getProjectId(), projectsItemDetailHistoryDetail.getItemId());
//                projectItem = ProjectsItemConverter.getEntity(projectsItemDetail);
//            }
//            ProjectsItemHistory projectsItemDetailHistory = ProjectsItemDetailHistoryConverter.getEntity(projectsItemDetailHistoryDetail);
//            projectsItemDetailHistory.setProjectsItem(projectItem);
//           // projectsItemDetailHistoryService.add(projectsItemDetailHistory);
//        }
        List<ProjectsItemHistoryDetail> projectsItemHistoryDetails = projectsItemDetailHistoryService.getAllProjectItemDetailHistoyByProjectIdAndItemId(projectsItemDetail.getProjectId(), projectsItemDetail.getItemId());

        Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").setPrettyPrinting().create();
        String jsonArray = gson.toJson(projectsItemHistoryDetails);

        JsonResult jr = new JsonResult(false, "Inser");
        return jsonArray;
    }

    // </editor-fold>
}
