/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.controller.hr;

import com.civil.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author rasel
 */
@Controller
public class LoginController {

    @Autowired
    UserService userService;
//    @Autowired
//    private CacheManager cacheManager;

    private static final Logger logger = Logger.getLogger(LoginController.class);

    @RequestMapping(value = {"/*", "/welcome**"}, method = RequestMethod.GET)
    public ModelAndView defaultPage() {

        System.out.println("1st");

        ModelAndView model = new ModelAndView();
        model.addObject("title", "RMS");
        model.addObject("message", "This is default page!");
        
        model.setViewName("index");

        return model;

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout, HttpServletRequest request, HttpSession session) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("errorMsg", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
        }

        if (logout != null) {

            if (session != null) {
                System.out.println("...................Session out");
                SecurityContextHolder.getContext().setAuthentication(null);
                session.invalidate();
                session = request.getSession();
                System.out.println("...................complete");
            }

            // cacheManager.getCache("userList").clear();
            // session.removeAttribute("title");
            //session.invalidate();
            model.addObject("msg", "You've been logged out successfully.");
        }

        //     model.addObject("lognSec", setServerSec(session));
        model.addObject("picture", "resources/img/civil.jpg");
        model.setViewName("login");
        System.out.println("Get");
        return model;

    }

    private String getErrorMessage(HttpServletRequest request, String key) {

        Exception exception = (Exception) request.getSession().getAttribute(key);

        String error = "";
        if (exception instanceof BadCredentialsException) {
            error = "Invalid username and password!";
        } else if (exception instanceof LockedException) {
            error = exception.getMessage();
        } else if (request.getSession().getAttribute("errorMessageForToken") != null) {
            error = request.getSession().getAttribute("errorMessageForToken").toString();
            request.getSession().removeAttribute("errorMessageForToken");
        } else {
            error = "Invalid username and password!";
        }

        return error;
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {

        ModelAndView model = new ModelAndView();

        // check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            model.addObject("username", userDetail.getUsername());

        }

        model.setViewName("403");
        return model;

    }
}
