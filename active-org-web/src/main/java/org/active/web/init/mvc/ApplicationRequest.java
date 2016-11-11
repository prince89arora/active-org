package org.active.web.init.mvc;

import org.active.web.init.security.UserPrinciple;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.security.Principal;

/**
 *
 */
public class ApplicationRequest extends HttpServletRequestWrapper {

    private HttpServletRequest request;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request
     * @throws IllegalArgumentException if the request is null
     */
    public ApplicationRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    public Principal getUserPrincipal() {
        if (request.getUserPrincipal() == null) {
            return new UserPrinciple("Prince");
        } else {
            return request.getUserPrincipal();
        }
    }


}
