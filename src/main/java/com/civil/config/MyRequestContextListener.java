/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.config;

import javax.servlet.annotation.WebListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;

/**
 *
 * @author rasel
 */
@Configuration
@WebListener
public class MyRequestContextListener extends RequestContextListener {
}
