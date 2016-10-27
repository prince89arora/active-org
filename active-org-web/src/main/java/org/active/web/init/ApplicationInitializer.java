package org.active.web.init;

import org.active.web.init.commons.ApplicationContextFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 *
 */
public class ApplicationInitializer implements ServletContainerInitializer {

    private static final Logger LOGGER = Logger.getLogger(ApplicationInitializer.class);

    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        ApplicationContextFactory.INIT.setServletContext(servletContext);
        LOGGER.info("Inside Application Initializer");
    }
}
