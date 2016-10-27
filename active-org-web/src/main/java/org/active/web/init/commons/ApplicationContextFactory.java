package org.active.web.init.commons;

import org.active.web.init.mvc.AbstratViewResolver;

import javax.servlet.ServletContext;

/**
 *
 */
public enum ApplicationContextFactory {

    INIT;

    private ServletContext servletContext;
    private AbstratViewResolver viewResolver;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public ServletContext getServletContext() {
        return this.servletContext;
    }

    public void setViewResolverFactory(AbstratViewResolver viewResolver) {
        this.viewResolver = viewResolver;
    }

    public AbstratViewResolver getViewResolver() {
        return this.viewResolver;
    }

}
