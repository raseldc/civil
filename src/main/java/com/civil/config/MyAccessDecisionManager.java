/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 see this blog
 https://www.programcreek.com/java-api-examples/?code=luotuo/springboot-security-wechat/springboot-security-wechat-master/src/main/java/com/jwcq/security/SecurityConfig.java#
 */
package com.civil.config;

import com.civil.model.Settings;
import com.civil.model.User;
import com.civil.service.MixService;
import com.civil.service.UserService;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.binary.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Service;

/**
 *
 * @author rasel
 */
@Service
public class MyAccessDecisionManager implements AccessDecisionManager {

    @Autowired
    UserService userService;
    @Autowired
    MixService mixService;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    protected boolean isRedirect(HttpServletRequest request,
            HttpServletResponse response, User user, String uri) {
        try {

            if (user.getStatus() != null && user.getStatus() == 3 && !uri.contains("changepassword")) {
                redirectStrategy.sendRedirect(request, response, "/user/changepassword");
                return true;
            }
            return false;
        } catch (IOException ex) {
            return false;
        }
    }

    private boolean isExtentionMatch(String uri) {
        List<String> ignoreExten = new ArrayList<>();
        ignoreExten.add("jpg");
        ignoreExten.add("js");
        ignoreExten.add("css");
        ignoreExten.add("map");
        ignoreExten.add("png");
        ignoreExten.add("woff2");
        ignoreExten.add("svg");
        int extIndex = uri.lastIndexOf('.');
        if (extIndex > 0) {
            String extention = uri.substring(extIndex + 1, uri.length());
            if (ignoreExten.contains(extention)) {

                return true;
            }
        }
        return false;
    }

    // set session attribute for loginUser
    public void sessionDataSet(HttpSession session, HttpServletRequest request) {
        User logedinUser = new User();
        if (session.getAttribute("User") == null) {
            org.springframework.security.core.userdetails.User user;
            user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String email = user.getUsername();
            logedinUser = userService.getUserByEmail(email);
            session.setAttribute("User", logedinUser);
        } else {
            logedinUser = (User) session.getAttribute("User");
        }

        if (session.getAttribute("menu") == null || session.getAttribute("menu").toString().length() == 0) {

            session.setAttribute("menu", userService.GetMenu(logedinUser.getEmail(), request.getContextPath()));
        }

        if (session.getAttribute("picId") == null) {
            session.setAttribute("picId", logedinUser.getEmail());
        }

        if (session.getAttribute("nameLoginUser") == null) {
            session.setAttribute("nameLoginUser", logedinUser.getFullName());
        }

        if (session.getAttribute("designation") == null) {
            session.setAttribute("designation", logedinUser.getEmployee() == null ? "" : logedinUser.getEmployee().getPost());
        }

        if (session.getAttribute("base64_pf") == null) {
            Settings s = mixService.getSettingsByKey("userProfilePicLocation");

            String ralPath = "";
            if (s != null) {
                try {
                    ralPath = s.getValue();
                    File pf = new File(ralPath + "" + logedinUser.getEmail() + ".jpg");

                    if (pf.exists()) {
                        BufferedImage bImage = ImageIO.read(pf);
                        ByteArrayOutputStream bos = new ByteArrayOutputStream();
                        ImageIO.write(bImage, "jpg", bos);
                        byte[] data = bos.toByteArray();
                        String base64String = Base64.encodeBase64String(data);
                        base64String = "data:image/png;base64," + base64String;
                        session.setAttribute("base64_pf", base64String);
                    } else {

                        session.setAttribute("base64_pf", request.getContextPath() + "/resources/img/Default.png");

                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }

    }

    //decide 方法是判定是否拥有权限的决策方法
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {

        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        HttpServletResponse response = ((FilterInvocation) object).getResponse();

        HttpSession session = request.getSession();
        String url, method;
        AntPathRequestMatcher matcher;
        String uri = request.getRequestURI().toLowerCase();
        if (isExtentionMatch(uri)) {
            return;
        }
        if (uri.split("\\.").length > 1) {
            return;
        }
        //String url_test = "";
        // String id = "";

        //  response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0, post-check=0, pre-check=0");
        for (GrantedAuthority ga : authentication.getAuthorities()) {
            if (new AntPathRequestMatcher("/").matches(request)) {
                return;
            }

            if (ga instanceof AuthorityInfo) {
//
                // User logedinUser = new User();

                sessionDataSet(session, request);

                if (isRedirect(request, response, (User) session.getAttribute("User"), uri)) {
                    return;
                }

                String contex_path = request.getContextPath().toLowerCase() + "/";
//                if (uri.equals(contex_path)) {
//                    return;
//                }
                AuthorityInfo urlGrantedAuthority = (AuthorityInfo) ga;
                url = urlGrantedAuthority.getUrl();
                if (url == null || url.isEmpty()) {
                    System.out.println("got got got");
                }
                method = urlGrantedAuthority.getMethod();
                matcher = new AntPathRequestMatcher(url);
                if (matcher.matches(request) || (uri.equals(contex_path))) {

                    //set session data 
                    // 当权限表权限的method为ALL时表示拥有此路径的所有请求方式权利。
                    if (method.equals(request.getMethod())) {
                        return;
                    }
                    return;
                }
            } else if (ga.getAuthority().equals("ROLE_ANONYMOUS")) {

                if ((new AntPathRequestMatcher("/user/register").matches(request) == true)
                        //                        || (new AntPathRequestMatcher("/reporter/report/*").matches(request) == true)
                        || (new AntPathRequestMatcher("/welcome").matches(request) == true)
                        || (new AntPathRequestMatcher("/user/registrationSumbit").matches(request) == true)) {
                    return;
                }

                matcher = new AntPathRequestMatcher("/login");

                if (matcher.matches(request)) {
                    return;
                }
            }
        }
        matcher = new AntPathRequestMatcher("/controller/**");
        // Tell web that you have no right!
        //throw new AccessDeniedException("no right");
        //System.out.println("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        //System.out.println("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        if ((new AntPathRequestMatcher("/data/**").matches(request) == true)
                || (new AntPathRequestMatcher("/forgetpassword**").matches(request) == true)
                    || (new AntPathRequestMatcher("/fileupload**").matches(request) == true)
                || (new AntPathRequestMatcher("/changepassword**").matches(request) == true)
                || (new AntPathRequestMatcher("/role/**").matches(request) == true)
                || (new AntPathRequestMatcher("/user/**").matches(request) == true)
                || (new AntPathRequestMatcher("/page/**").matches(request) == true)
                || (new AntPathRequestMatcher("/item/**").matches(request) == true)
                || (new AntPathRequestMatcher("/project/**").matches(request) == true)
                || (new AntPathRequestMatcher("/item/**").matches(request) == true)
                || (new AntPathRequestMatcher("/project/**").matches(request) == true)
                || (new AntPathRequestMatcher("/dashboard/**").matches(request) == true)
                || (new AntPathRequestMatcher("/cne/**").matches(request) == true)
                || request.getRequestURI().equals("/logout") || request.getRequestURI().equals("/contorel")
                || (new AntPathRequestMatcher("/welcome").matches(request) == true)
                || request.getRequestURI().equals("/luotuo/user/join")
                || request.getRequestURI().equals("/luotuo/user/wechatJoin")) {

            return;
        }

        System.out.println("AccessDeniedException: no right , URL : " + uri.toString());
        throw new AccessDeniedException("no right");

    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

}
