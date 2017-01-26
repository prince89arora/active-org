package org.active.web.init;

import org.active.security.SecurityInitializer;
import org.active.services.core.ContextLoader;
import org.active.web.init.commons.ApplicationContextFactory;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

/**
 *
 */
@WebListener("Jersey Init")
public class JerseyInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContainer servletContainer = new ServletContainer();
        ServletRegistration.Dynamic dynamic = sce.getServletContext().addServlet("jersey", servletContainer);
        dynamic.addMapping("/rest/*");
        dynamic.setInitParameter("jersey.config.server.provider.packages", "org.active.web.init.resources");
        dynamic.setAsyncSupported(true);

        ContextLoader.loadServices("org.active.services.ref");
        this.initializeContext(sce.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //doing nothing.
    }

    private void initializeContext(ServletContext servletContext) {
        ApplicationContextFactory.INIT.setServletContext(servletContext);

        //Initialising security
        SecurityInitializer.init().url("/rest/auth/login").authorized(false)
                .add().url("/rest/auth/logout").authorized(true).add().secure();
        ApplicationContextFactory.INIT.setSecurityContext(SecurityInitializer.getSecurityContext());
    }
}
