package org.active.web.init.commons;

/**
 * Http response status constants.
 *
 * @author princearora
 */
public class HttpStatus {

    public static class Error {
        public static final int NOT_FOUND = 404;
        public static final int INTERNAL_ERROR = 500;
        public static final int UNAUTHORIZED_ERROR = 403;
    }

    public static final int ok = 200;
}
