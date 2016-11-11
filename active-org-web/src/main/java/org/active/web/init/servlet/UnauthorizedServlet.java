package org.active.web.init.servlet;

import org.active.web.init.commons.ApplicationContextFactory;
import org.active.web.init.mvc.Model;
import org.active.web.init.mvc.Response;
import org.active.web.init.mvc.Viewable;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.active.web.init.commons.ApplicationConstants.UNAUTHORIZED;

/**
 *
 */
@WebServlet(urlPatterns = {UNAUTHORIZED}, name = "403 Handling")
public class UnauthorizedServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(UnauthorizedServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Model model  = new Model();
        Viewable viewable = new Viewable("403", model);
        ApplicationContextFactory.INIT.getViewResolver()
                .render(Response.builder()
                .httpResponse(response)
                .request(request).viewable(viewable).build());
    }
}
