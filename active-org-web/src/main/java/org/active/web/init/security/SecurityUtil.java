package org.active.web.init.security;

import org.active.web.init.commons.HttpMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 *
 */
public class SecurityUtil {

    /**
     *
     * @param request
     * @param methods
     * @return
     */
    public static boolean checkRequestMethod(HttpServletRequest request, HttpMethod[] methods) {
        String method = request.getMethod().toLowerCase();

        switch (method) {
            case "get" :
                return (Arrays.asList(methods).contains(HttpMethod.GET)) ? true : false;
            case "post" :
                return (Arrays.asList(methods).contains(HttpMethod.POST)) ? true : false;
            case "put" :
                return (Arrays.asList(methods).contains(HttpMethod.PUT)) ? true : false;
            default :
                return false;
        }
    }
}
