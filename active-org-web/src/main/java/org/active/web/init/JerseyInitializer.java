package org.active.web.init;

import org.glassfish.jersey.servlet.ServletContainer;

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
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //doing nothing.
    }
}
