package org.servlet;

import org.manager.command.Command;
import org.manager.command.LogInCommand;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.Constants.*;

public class Servlet extends HttpServlet {
    private Map<String, Command> commands = new HashMap<>();
    private Map<String, String> paths = new HashMap<>();

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);

        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());

//        commands.put("logout",
//                new LogOutCommand());
        commands.put("login",
                new LogInCommand());
//        commands.put("exception" , new ExceptionCommand());

        paths.put("/WEB-INF/jsp/home.jsp", "/tax_system/home");

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
        String path = request.getRequestURI();
        Command command = commands.getOrDefault(
                path.substring(path.lastIndexOf(SEPARATOR) + 1),
                (r)->"/tax_system/home"); //"/WEB-INF/jsp/home.jsp" - for forward()
        System.out.println(command.getClass().getName());
//        String page = command.execute(request);
//        String str = path.replaceAll(path.substring(path.lastIndexOf(SEPARATOR)), "");
        String str = command.execute(request);
        response.sendRedirect(str);  // "/tax_system/home"
        //request.getRequestDispatcher(page).forward(request,response);
    }

}
