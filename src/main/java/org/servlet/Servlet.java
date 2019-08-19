package org.servlet;

import org.TableOfURI;
import org.apache.log4j.Logger;
import org.manager.command.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static org.Constants.*;

public class Servlet extends HttpServlet {
    private static final Logger LOGGER =
            Logger.getLogger(Servlet.class);

    private Map<String, Command> commands = new HashMap<>();

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);

        Set<String> hSet = Collections.newSetFromMap(
                new ConcurrentHashMap<String, Boolean>());
        servletConfig.getServletContext().setAttribute(
                LOGGED_USERS_PARAMETER, hSet);
        commands.put(LOGIN_COMMAND, new LogInCommand());
        commands.put(LOGOUT_COMMAND, new LogOutCommand());
        commands.put(REGISTRATION_COMMAND, new RegistrationCommand());
        commands.put(MAKE_REPORT_COMMAND, new MakeReportCommand());
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {
        Command command;
        String path = request.getRequestURI();
        if (request.getSession().getAttribute(LOGOUT_COMMAND) == null) {
            command = commands.getOrDefault(
                    path.substring(path.lastIndexOf(SEPARATOR) + 1),
                    (r)->TableOfURI.HOME.getPagePath());
        } else {
            request.getSession().removeAttribute(LOGOUT_COMMAND);
            command = commands.get(LOGOUT_COMMAND);
        }
        LOGGER.info(command.getClass().getName());
        response.sendRedirect(command.execute(request));
    }

}
