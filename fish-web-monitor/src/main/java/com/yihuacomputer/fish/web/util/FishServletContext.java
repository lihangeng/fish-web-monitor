package com.yihuacomputer.fish.web.util;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

public class FishServletContext implements ServletContextAware{

    private ServletContext servletContext; 
    
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public ServletContext getServletContext() {
        return servletContext;
    }

}
