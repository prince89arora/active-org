package org.active.web.init.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 */
public class ActiveOrgDispatcherServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        if (request.getHeader("dispatched") == null) {
            response.addHeader("dispatched", "true");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/index.jsp");
            dispatcher.forward(request, response);
        }
    }

}
