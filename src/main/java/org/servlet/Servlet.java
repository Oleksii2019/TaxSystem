package org.servlet;

import org.manager.command.Command;
import org.manager.command.LogInCommand;
import org.manager.command.LogOutCommand;

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
    private Map<String, Command> commands = new HashMap<>();

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);

        Set<String> hSet = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());
        servletConfig.getServletContext().setAttribute(LOGGED_USERS_PARAMETER, hSet);

        //        servletConfig.getServletContext()
//                .setAttribute(LOGGED_USERS_PARAMETER, new HashSet<String>());

//        commands.put("logout",
//                new LogOutCommand());
        commands.put("login", new LogInCommand());
        commands.put(LOGOUT_COMMAND, new LogOutCommand());
//        commands.put("exception" , new ExceptionCommand());

    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("get go");
        processRequest(request, response);
        //response.getWriter().print("Hello from servlet");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command;
        String path = request.getRequestURI();

        if (request.getAttribute("logout") == null) {
            command = commands.getOrDefault(
                    path.substring(path.lastIndexOf(SEPARATOR) + 1),
                    (r)->"/tax_system/home"); //"/WEB-INF/jsp/home.jsp" - for forward()

        } else { // Принудительный logout
            request.removeAttribute(LOGOUT_COMMAND);
            command = commands.get(LOGOUT_COMMAND); //.getOrDefault("logout", (r)->"/tax_system/home");
        }

        System.out.println(command.getClass().getName());
//        String page = command.execute(request);
//        String str = path.replaceAll(path.substring(path.lastIndexOf(SEPARATOR)), "");
        String str = command.execute(request);
        response.sendRedirect(str);  // "/tax_system/home"
        //request.getRequestDispatcher(page).forward(request,response);
    }

}
