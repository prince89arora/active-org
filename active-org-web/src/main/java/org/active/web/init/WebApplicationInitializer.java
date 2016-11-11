package org.active.web.init;

import org.active.web.init.commons.ApplicationContextFactory;
import org.active.web.init.commons.HttpMethod;
import org.active.web.init.mvc.ViewResolverConfig;
import org.active.web.init.mvc.resolvers.JspViewResolver;
import org.active.web.init.security.SecurityInitializer;
import org.apache.log4j.Logger;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * Application initializer, preparing context and configuration
 * for view resolver for application.
 *
 * @author princearora
 */
public class WebApplicationInitializer implements ServletContainerInitializer {

    private static final Logger log = Logger.getLogger(WebApplicationInitializer.class);

    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        ApplicationContextFactory.INIT.setServletContext(servletContext);
        ApplicationContextFactory.INIT.setViewResolverFactory(new JspViewResolver(
                new ViewResolverConfig("/views/", "jsp")
        ));

        /**
         * Initialising web security for all the url mappings.
         */
        SecurityInitializer.init().url("/").authorized(false)
                .httpMethods(new HttpMethod[]{HttpMethod.GET})
                .add()
                .url("/hello/*").authorized(false).httpMethods(new HttpMethod[]{HttpMethod.GET})
                .add()
                .url("/static/*").authorized(false).httpMethods(new HttpMethod[]{HttpMethod.GET})
                .add().secure();
    }
}
