package org.active.web.init.servlet;

import org.active.web.init.commons.ApplicationContextFactory;
import org.active.web.init.mvc.HttpStatus;
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

/**
 *
 */
@WebServlet(urlPatterns = {"/hello"})
public class TestServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(TestServlet.class);

    protected void doGet(HttpServletRequest httpRequest, HttpServletResponse httpResponse)
            throws ServletException, IOException {
        Model model = new Model();
        model.addAttribute("title", "Home Page");
        Viewable viewable = new Viewable("index", model);

        ApplicationContextFactory.INIT.getViewResolver()
                .render(Response.builder().request(httpRequest).httpResponse(httpResponse)
                        .httpStatus(HttpStatus.ok).viewable(viewable).build());
    }
}
