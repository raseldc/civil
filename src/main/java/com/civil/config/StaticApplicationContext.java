/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.civil.config;

import org.apache.tiles.request.ApplicationContext;
import org.apache.tiles.request.ApplicationContextAware;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Rasel
 */
@Configuration
public class StaticApplicationContext  implements ApplicationContextAware{

  static ApplicationContext applicationContext = null;

  public void setApplicationContext(ApplicationContext context)    throws BeansException {
    applicationContext = context;
  }
  /**
   * Note that this is a static method which expose ApplicationContext
   **/
  public static ApplicationContext getContext(){
        return applicationContext;
  }

}