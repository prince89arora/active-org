package org.active.security.filters;

import org.active.security.ApplicationRequest;
import org.active.security.SecurityInitializer;

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

/**
 * @author princearora
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

        if (!SecurityInitializer.getSecurityContext().validateRequest(request)) {
            response.sendError(403);
            return;
        }
        filterChain.doFilter(new ApplicationRequest(request), response);
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

}
