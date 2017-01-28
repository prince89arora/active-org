package org.active.web.init;

import org.active.security.SecurityInitializer;
import org.active.services.core.ContextLoader;
import org.active.web.init.commons.ApplicationContextFactory;
import org.apache.log4j.Logger;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

/**
 *
 */
public class WebApplicationInitializer implements ServletContainerInitializer {

    private static final Logger log = Logger.getLogger(WebApplicationInitializer.class);

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        ServletContainer servletContainer = new ServletContainer();
        ServletRegistration.Dynamic dynamic = ctx.addServlet("jersey", servletContainer);
        dynamic.addMapping("/rest/*");
        dynamic.setInitParameter("jersey.config.server.provider.packages", "org.active.web.init.resources");
        dynamic.setAsyncSupported(true);

        ContextLoader.loadServices("org.active.services.ref");
        this.initializeContext(ctx);
    }

    private void initializeContext(ServletContext servletContext) {
        ApplicationContextFactory.INIT.setServletContext(servletContext);

        //Initialising security
        SecurityInitializer.init().url("/rest/auth/login").authorized(false)
                .add().url("/rest/auth/logout").authorized(true).add()
                .url("/rest/auth/test").authorized(true).add()
                .url("/rest/auth").authorized(false).add().secure();
        ApplicationContextFactory.INIT.setSecurityContext(SecurityInitializer.getSecurityContext());
    }
}
