package org.active.web.init.servlet;

import org.active.web.init.commons.ApplicationContextFactory;
import org.active.web.init.commons.HttpStatus;
import org.active.web.init.mvc.Model;
import org.active.web.init.mvc.Response;
import org.active.web.init.mvc.Viewable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
@WebServlet(urlPatterns = {"/"})
public class RootContextServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getPathInfo() == null || request.getPathInfo().equals("/")) {
            Model model = new Model();
            Viewable viewable = new Viewable("index", model);
            ApplicationContextFactory.INIT.getViewResolver()
                    .render(Response.builder().request(request).httpResponse(response)
                            .httpStatus(HttpStatus.ok).viewable(viewable).build());
        }
    }
}
