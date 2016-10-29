package org.active.web.init.mvc.resolvers;

import org.active.web.init.exception.TemplateNotFoundException;
import org.active.web.init.mvc.AbstractViewResolver;
import org.active.web.init.mvc.ViewResolverConfig;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Implementation for {@link AbstractViewResolver} to resolve jsp views
 * and return serve respective jsps as servlet response.
 *
 * @author princearora
 */
public class JspViewResolver extends AbstractViewResolver {

    private static final Logger log = Logger.getLogger(JspViewResolver.class);

    public JspViewResolver(String basePath, String extension) {
        this.basePath = basePath;
        this.extension = extension;
        this.resolverConfig = new ViewResolverConfig(basePath, extension);
    }

    public JspViewResolver(ViewResolverConfig resolverConfig) {
        this.resolverConfig = resolverConfig;
        this.basePath = resolverConfig.getBasePath();
        this.extension = resolverConfig.getExtension();
    }

    @Override
    protected void prepareResponse() throws ServletException, TemplateNotFoundException, IOException {
        if (!response.getViewable().isResolved()) {
            throw new TemplateNotFoundException(response.getViewable().getFullTemplatePath());
        }
        response.getResponse().setContentType(RESPONSE_TYPE);
    }

}
