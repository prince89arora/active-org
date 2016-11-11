package org.active.web.init.filters;

import org.active.web.init.commons.ApplicationContextFactory;
import org.active.web.init.mvc.ApplicationRequest;
import org.active.web.init.security.SecurityMapping;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.active.web.init.commons.ApplicationConstants.UNAUTHORIZED;

/**
 *
 */
@WebFilter(urlPatterns = {"/", "/**", "/*"}, filterName = "Security Filter")
public class ApplicationSecurityFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getServletPath().equals(UNAUTHORIZED)) {
            //DO nothing
        } else {
            SecurityMapping securityMapping = ApplicationContextFactory.INIT.getSecurityContext()
                    .findMapping(request.getServletPath());
            if (securityMapping.isAuthorized()) {
                if (!ApplicationContextFactory.INIT.getSecurityContext().validateRequest(request)) {
                    response.sendRedirect(ApplicationContextFactory.INIT.getServletContext().getContextPath()
                            + UNAUTHORIZED);
                    return;
                }
            }
        }

        filterChain.doFilter(new ApplicationRequest(request), response);
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

}
