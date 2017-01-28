package org.active.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.security.Principal;
import java.util.Map;

/**
 * @author princearora
 */
public class ApplicationRequest extends HttpServletRequestWrapper {

    private HttpServletRequest request;
    private Map<String, String[]> parameterMap;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request
     * @throws IllegalArgumentException if the request is null
     */
    public ApplicationRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
        this.parameterMap = request.getParameterMap();
    }

    public Principal getUserPrincipal() {
        if (request.getUserPrincipal() == null) {
            User user = SecurityInitializer.getSecurityContext().getUserFromRequest(request);
            if (user != null) {
                return new UserPrinciple(user.getUserId());
            }
            return null;
        } else {
            return request.getUserPrincipal();
        }
    }

    public String getParameter(String key) {
        String[] value = this.parameterMap.get(key);
        return value[0];
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return parameterMap;
    }
}