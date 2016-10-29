package org.active.web.init.mvc;

import org.active.web.init.commons.ApplicationContextFactory;
import org.active.web.init.exception.TemplateNotFoundException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Abstract view resolver for servlet requests. Implement to add new
 * Resolver type for servlet responses.
 *
 * @author princearora
 */
public abstract class AbstractViewResolver {

    private static final Logger log = Logger.getLogger(AbstractViewResolver.class);

    protected String basePath;
    protected String extension;
    protected ViewResolverConfig resolverConfig;
    protected Response response;

    protected static final String RESPONSE_TYPE = "text/html";

    private void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    private void setExtension(String extension) {
        this.extension = extension;
    }

    private void setResolverConfig(ViewResolverConfig resolverConfig) {
        this.resolverConfig = resolverConfig;
    }

    /**
     * Resolve Viewable by checking the template availability from given base path
     * and prepare model for response. Updating Viewable model to full template path
     * and status for renderable resource. {@link Viewable}
     *
     * @param viewable
     * @param request
     * @throws TemplateNotFoundException
     */
    private void resolveViewable(Viewable viewable, HttpServletRequest request)
            throws TemplateNotFoundException {
        String fullTemplatePath = this.prepareTemplatePath(viewable.getTemplate());

        try {
            if (ApplicationContextFactory.INIT.getServletContext().getResource(fullTemplatePath) != null) {
                viewable.setResolved(true);
            } else {
                viewable.setResolved(false);
            }
            this.prepareRequestAttributes(request, viewable.getModel());
            viewable.setFullTemplatePath(fullTemplatePath);
        } catch (MalformedURLException e) {
            throw new TemplateNotFoundException(fullTemplatePath, e);
        }
    }

    /**
     * Prepare response according to view resolver implementation
     * that will be handled by render to give back servlet response.
     *
     * @throws ServletException
     * @throws TemplateNotFoundException
     * @throws IOException
     */
    protected abstract void prepareResponse() throws ServletException, TemplateNotFoundException,
            IOException;

    /**
     * Prepare error response in case of missing template or any internal issue.
     *
     * @param request
     * @param response
     * @param message
     */
    protected void errorResponse(HttpServletRequest request, HttpServletResponse response,
                                       String message) {
        try {
            Model model = new Model();
            model.addAttribute("httpStatus", HttpStatus.Error.INTERNAL_ERROR);
            model.addAttribute("message", message);
            Viewable viewable = new Viewable(String.valueOf(HttpStatus.Error.INTERNAL_ERROR), model);
            this.resolveViewable(viewable, request);
            if (viewable.isResolved()) {
                response.setContentType(RESPONSE_TYPE);
                RequestDispatcher dispatcher = request.getRequestDispatcher(viewable.getFullTemplatePath());
                dispatcher.forward(request, response);
            }
        } catch (TemplateNotFoundException | IOException | ServletException e) {
            log.error("Exception occured while preparing error response: ", e);
            try {
                response.getWriter().write(message);
            } catch (IOException ioe) {

            }
        }
    }

    /**
     * Prepare and Send back servlet response according to given Response
     * {@link Response}
     *
     * @param response
     */
    public void render(Response response) {
        this.response = response;
        try {
            this.resolveViewable(response.getViewable(), response.getRequest());
            this.prepareResponse();
            RequestDispatcher dispatcher = response.getRequest().getRequestDispatcher(response
                    .getViewable().getFullTemplatePath());
            dispatcher.forward(response.getRequest(), response.getResponse());
        } catch (TemplateNotFoundException | ServletException | IOException ex) {
            this.errorResponse(response.getRequest(), response.getResponse(), ex.getMessage());
        }
    }

    /**
     * Preparing full template path that will be resolved for response.
     *
     * @param name
     * @return
     */
    private String prepareTemplatePath(String name) {
        String fullPath = this.basePath;
        if (fullPath.endsWith("/")) {
            fullPath = fullPath + name;
        } else {
            fullPath = fullPath + "/" + name;
        }

        if (this.extension.startsWith(".")) {
            fullPath = fullPath + this.extension;
        } else {
            fullPath = fullPath + "." + this.extension;
        }
        return fullPath;
    }

    /**
     * Prepare all module attributes so that they can be accesses in view
     * template.
     *
     * @param request
     * @param model
     */
    private void prepareRequestAttributes(HttpServletRequest request, Model model) {
        if (model != null) {
            model.forEach((key, value) -> {
                if (value != null) {
                    request.setAttribute(key, value);
                }
            });
        }
    }
}
