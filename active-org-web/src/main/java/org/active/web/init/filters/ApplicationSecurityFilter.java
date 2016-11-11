package org.active.web.init.filters;

import org.active.web.init.commons.ApplicationContextFactory;
import org.active.web.init.commons.HttpStatus;
import org.active.web.init.mvc.ApplicationRequest;
import org.active.web.init.security.SecurityMapping;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        SecurityMapping securityMapping = ApplicationContextFactory.INIT.getSecurityContext()
                .findMapping(request.getServletPath());
        if (securityMapping.isAuthorized()) {
            if (!ApplicationContextFactory.INIT.getSecurityContext().validateRequest(request)) {
                response.setStatus(HttpStatus.Error.UNAUTHORIZED_ERROR);
                return;
            }
        }
        filterChain.doFilter(new ApplicationRequest(request), response);
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }

    private Cookie isSecured(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie loginCookie = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("loginid")) {
                loginCookie = cookie;
                break;
            }
        }
        return loginCookie;
    }

    private void getSecurityConfig() {

    }
}
