/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.controllers;

import com.civil.model.Page;
import com.civil.model.User;
import com.civil.service.MixService;
import com.civil.service.PageService;
import com.civil.service.UserService;
import com.civil.util.PageDetailPropertyEditor;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 *
 * @author rasel
 */
@Controller
public class MixController {

    @Autowired
    MixService mixService;
    @Autowired
    PageService pageService;

    @Autowired
    UserService userService;

   

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Page.class, new PageDetailPropertyEditor(pageService));

      

        //  binder.registerCustomEditor(ComplaintReasonDetail.class, new ComplaintReasonPropertyEditor());
    }

}
