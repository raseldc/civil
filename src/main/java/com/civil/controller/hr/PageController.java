/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.controller.hr;

import com.civil.converter.PageConverter;
import com.civil.detail.PageDetail;
import com.civil.model.Page;
import com.civil.service.PageService;
import com.civil.util.Comparator.PageDetailCustomComparator;
import com.civil.util.PageDetailPropertyEditor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
public class PageController {

    // <editor-fold defaultstate="collapsed" desc="Page add edit">
    @Autowired
    PageService pageService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {

        binder.registerCustomEditor(Page.class, new PageDetailPropertyEditor(pageService));

        //  binder.registerCustomEditor(ComplaintReasonDetail.class, new ComplaintReasonPropertyEditor());
    }

    public ModelAndView generalModelandViewControll(ModelAndView modelAndView, String msg) {
        modelAndView.addObject("action", "page/pageform");
        modelAndView.addObject("model", new PageDetail());
        List<String> pageType = new ArrayList<>();
        pageType.add("0"); // O for parent page
        pageType.add("9");// 9 for header
        pageType.add("8");//8 for sub header
        pageType.add("7");
        List<PageDetail> parentPageDetails = pageService.getAllParentPage(0, 1000, pageType);
        List<PageDetail> parentPageDetails_ = new ArrayList<>();

        parentPageDetails.forEach(a -> {
            try {
                PageDetail temp = (PageDetail) a.clone();
                temp.setName(temp.getName() + "|" + temp.getType());
                parentPageDetails_.add(temp);

            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Collections.sort(parentPageDetails, PageDetailCustomComparator.NameComparator);
        modelAndView.addObject("pPageList", parentPageDetails);
        modelAndView.addObject("pPageList_", parentPageDetails_);
        modelAndView.addObject("msg", msg);

        modelAndView.setViewName("/hr/page/pageform");
        return modelAndView;
    }

    @RequestMapping(value = {"/page/pageform"}, method = RequestMethod.GET)
    public ModelAndView controllerForm() {
        System.out.println("User Reg");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView = generalModelandViewControll(modelAndView, "");
        System.out.println("lang " + LocaleContextHolder.getLocale().getLanguage());

        return modelAndView;
    }

    @RequestMapping(value = {"/page/pageform"}, method = RequestMethod.POST)
    public ModelAndView controllerSubmit(@Valid PageDetail controllerDetail, BindingResult result, Model model, HttpServletRequest request) {

        System.out.println("controllerService Reg");
        Page page = PageConverter.getEntity(controllerDetail);
        String pageDetailId = request.getParameter("pageDetail");
        int parentPageId = Integer.parseInt(pageDetailId);
        Page parentPage = new Page(parentPageId);
        if (parentPageId != 0) {
            page.setPage(parentPage);
        }
        if (page.getId() != null) {

            if (page.getUrl().equals("//")) {
                page.setType(0);
            }
            pageService.update(page);
        } else {
            if (page.getUrl() == "//") {
                page.setType(0);
            }

            pageService.add(page);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView = generalModelandViewControll(modelAndView, "");

//        modelAndView.addObject("action", "/roll/rollAdd");
//        modelAndView.addObject("model", new RoleDetail());
//        modelAndView.setViewName("role");
        return modelAndView;
    }

    @RequestMapping(value = "/page/pageall")
    @ResponseBody
    public String getAllPage(@RequestParam(value = "pId", required = false) String pId, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {

        String startIndexSt = request.getParameter("start");
        int startPageIndex = startIndexSt != null ? Integer.parseInt(startIndexSt) : 0;
        String recordsPerPageSt = request.getParameter("length");
        int recordsPerPage = recordsPerPageSt != null ? Integer.parseInt(recordsPerPageSt) : Integer.MAX_VALUE;
        int draw = Integer.parseInt(request.getParameter("draw"));
        int parentId = Integer.parseInt(pId);

        List<PageDetail> pageDetailList = pageService.getAllPageDetail(startPageIndex, recordsPerPage, parentId);

//        for (Page p : pageList) {
//            if (parentId != 0) {
//                if (p.getPage() != null && p.getPage().getId() == parentId) {
//                    pageDetailList.add(PageConverter.getDetail(p));
//                }
//            } else if (parentId == 0) {
//                pageDetailList.add(PageConverter.getDetail(p));
//            }
//
//        }
        Collections.sort(pageDetailList, new PageDetailCustomComparator());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonArray = gson.toJson(pageDetailList);
        // jsonArray = "{\"Result\":\"OK\",\"Records\":" + jsonArray + ",\"TotalRecordCount\":" + userSize + "}";
        //   jsonArray = "{\"data\":" + jsonArray + "}";

        jsonArray = "{ \"draw\": " + draw + ",\"recordsTotal\": " + pageDetailList.size() + ",\"recordsFiltered\": " + pageService.getCount() + ",\"data\":" + jsonArray + "}";

        System.out.println("Ajax " + jsonArray);
        return jsonArray;
    }

    @RequestMapping(value = "/page/pageallbytype")
    @ResponseBody
    public String pageallbytype(@RequestParam(value = "pageType", required = false) String pageType, HttpServletRequest request, HttpServletResponse response, HttpSession httpSession) {

        String startIndexSt = request.getParameter("start");
        int startPageIndex = startIndexSt != null ? Integer.parseInt(startIndexSt) : 0;
        String recordsPerPageSt = request.getParameter("length");
        int recordsPerPage = recordsPerPageSt != null ? Integer.parseInt(recordsPerPageSt) : Integer.MAX_VALUE;
        int draw = Integer.parseInt(request.getParameter("draw"));
        int pageType_int = Integer.parseInt(pageType);

        List<PageDetail> pageDetailList = pageService.getAllPageDetailByPageType(startPageIndex, recordsPerPage, pageType_int);

        Collections.sort(pageDetailList, new PageDetailCustomComparator());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonArray = gson.toJson(pageDetailList);

        jsonArray = "{ \"draw\": " + draw + ",\"recordsTotal\": " + pageDetailList.size() + ",\"recordsFiltered\": " + pageService.getCount() + ",\"data\":" + jsonArray + "}";

        System.out.println("Ajax " + jsonArray);
        return jsonArray;
    }

    // </editor-fold>
}
