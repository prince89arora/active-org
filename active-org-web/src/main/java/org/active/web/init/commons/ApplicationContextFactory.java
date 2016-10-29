package org.active.web.init.commons;

import org.active.web.init.mvc.AbstractViewResolver;

import javax.servlet.ServletContext;

/**
 * Application context factory to contain application level
 * information and configurations used to initialise
 * application.
 *
 * @author princearora
 */
public enum ApplicationContextFactory {

    INIT;

    private ServletContext servletContext;
    private AbstractViewResolver viewResolver;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public ServletContext getServletContext() {
        return this.servletContext;
    }

    public void setViewResolverFactory(AbstractViewResolver viewResolver) {
        this.viewResolver = viewResolver;
    }

    public AbstractViewResolver getViewResolver() {
        return this.viewResolver;
    }

}
