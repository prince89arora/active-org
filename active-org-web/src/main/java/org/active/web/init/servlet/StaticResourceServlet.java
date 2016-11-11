package org.active.web.init.servlet;

import org.active.web.init.commons.ApplicationContextFactory;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 */
@WebServlet(urlPatterns = "/static/*")
public class StaticResourceServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(StaticResourceServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String resourcePath = request.getServletPath() + request.getPathInfo();
        try (InputStream stream = ApplicationContextFactory.INIT.getServletContext()
                .getResourceAsStream(resourcePath)) {
            //response.setContentType("application/octet-stream");
            IOUtils.copy(stream, response.getOutputStream());
        } catch (Exception ex) {
            log.error("Cannot serve content : ", ex);
            response.setStatus(404);
        }

    }
}
