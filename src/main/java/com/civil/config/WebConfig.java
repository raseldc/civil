/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.config;

import com.civil.dao.GenericDao;
import com.civil.dao.ItemDaoImpl;
import com.civil.dao.PageDaoImpl;
import com.civil.dao.ProjectItemDaoImpl;
import com.civil.dao.ProjectsItemHistoryDaoImpl;
import com.civil.dao.RoleDaoImpl;
import com.civil.dao.RolePageDaoImpl;
import com.civil.dao.UserDaoImpl;

import com.civil.service.GenericService;
import com.civil.service.ItemServiceImpl;
import com.civil.service.PageServiceImpl;
import com.civil.service.ProjectItemServiceImpl;
import com.civil.service.ProjectsItemHistoryServiceImpl;
import com.civil.service.RolePageServiceImpl;
import com.civil.service.RoleServiceImpl;

import com.civil.service.UserServiceImpl;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.http.CacheControl;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/**
 *
 * @author Rasel
 */
@EnableWebMvc
@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.civil.*"})
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/pages/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    private static final int BROWSER_CACHE_CONTROL = 604800;

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(BROWSER_CACHE_CONTROL);
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCacheControl(CacheControl.maxAge(1, TimeUnit.DAYS)).setCachePeriod(BROWSER_CACHE_CONTROL);
    }

    /**
     * <code>Resolves views selected for rendering by @Controllers to tiles resources in the Apache TilesConfigurer bean</code>
     */
    @Bean
    public TilesViewResolver getTilesViewResolver() {
        TilesViewResolver tilesViewResolver = new TilesViewResolver();
        tilesViewResolver.setViewClass(TilesView.class);
        return tilesViewResolver;
    }

    /**
     * <code>Configures Apache tiles definitions bean used by Apache TilesViewResolver to resolve views selected for rendering by @Controllers</code>
     */
    @Bean
    public TilesConfigurer getTilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setCheckRefresh(true);
        tilesConfigurer.setDefinitions("/WEB-INF/layouts/layouts.xml");
//        tilesConfigurer.setDefinitionsFactoryClass(TilesDefinitionsConfig.class);

        // Add apache tiles definitions
        TilesDefinitionsConfig.addDefinitions();

        return tilesConfigurer;
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getResolver() throws IOException {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();

        //Set the maximum allowed size (in bytes) for each individual file.
        resolver.setMaxUploadSizePerFile(5242880);//5MB

        //You may also set other available properties.
        return resolver;
    }

    @Bean(name = "userDao")
    public GenericDao userDao() {
        return new UserDaoImpl();
    }

    @Bean(name = "userService")
    @Scope("prototype")
    public GenericService userService() {
        return new UserServiceImpl(userDao());
    }

    @Bean(name = "roleDao")
    public GenericDao roleDao() {
        return new RoleDaoImpl();
    }

    @Bean(name = "roleService")
    @Scope("prototype")
    public GenericService roleService() {
        return new RoleServiceImpl(roleDao());
    }

    @Bean(name = "pageDao")
    public GenericDao pageDao() {
        return new PageDaoImpl();
    }

    @Bean(name = "pageService")
    public GenericService pageService() {
        return new PageServiceImpl(pageDao());
    }

    @Bean(name = "rolePageDao")
    @Qualifier("rolePageDaoImpl")
    public GenericDao rolePageDao() {
        return new RolePageDaoImpl();
    }

    @Bean(name = "rolePageService")
    @Scope("prototype")
    public GenericService rolePageService() {
        return new RolePageServiceImpl(rolePageDao());
    }

    @Bean(name = "itemDao")
    public GenericDao itemDao() {
        return new ItemDaoImpl();
    }

    @Bean(name = "itemService")
    public GenericService itemService() {
        return new ItemServiceImpl(itemDao());
    }

    @Bean(name = "projectItemDao")
    public GenericDao projectItemDao() {
        return new ProjectItemDaoImpl();
    }

    @Bean(name = "projectItemService")
    public GenericService projectItemService() {
        return new ProjectItemServiceImpl(projectItemDao());
    }

    @Bean(name = "projectsItemHistoryDao")
    public GenericDao projectsItemHistoryDao() {

        return new ProjectsItemHistoryDaoImpl();
    }

    @Bean(name = "projectsItemHistoryService")
    public GenericService projectsItemlHistoryService() {
        return new ProjectsItemHistoryServiceImpl(projectsItemHistoryDao());
    }

}
