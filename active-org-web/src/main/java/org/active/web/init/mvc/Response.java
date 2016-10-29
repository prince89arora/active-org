package org.active.web.init.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Application http response object.
 *
 * @author princearora
 */
public class Response {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private Viewable viewable;
    private int httpStatus;

    private Response() {}

    public static Builder builder() {
        return new Builder(new Response());
    }

    public HttpServletRequest getRequest() {
        return this.request;
    }

    public HttpServletResponse getResponse() {
        return this.response;
    }

    public Viewable getViewable() {
        return this.viewable;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public static class Builder {

        private Response response;

        public Builder(Response response) {
            this.response = response;
        }

        public Builder httpResponse(HttpServletResponse httpResponse) {
            this.response.response = httpResponse;
            return this;
        }

        public Builder request(HttpServletRequest request) {
            this.response.request = request;
            return this;
        }

        public Builder viewable(Viewable viewable) {
            this.response.viewable = viewable;
            return this;
        }

        public Builder httpStatus(int httpStatus) {
            this.response.httpStatus = httpStatus;
            return this;
        }

        public Response build() {
            return this.response;
        }
    }

}
