package org.manager.command;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;

public class CommandUtility {
    static void setUserRole(HttpServletRequest request,
                            String role, String name) {
        HttpSession session = request.getSession();
        ServletContext context = session.getServletContext();
        context.setAttribute("userName", name);
        session.setAttribute("role", role);
    }

    static boolean IsUserLogged(HttpServletRequest request, String userName) {
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession()
                .getServletContext().getAttribute("loggedUsers");
        return loggedUsers.stream().anyMatch(userName::equals);
    }

    static void addToLoggedUsers(HttpServletRequest request, String userName) {
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession()
                .getServletContext().getAttribute("loggedUsers");
        loggedUsers.add(userName);

        // TODO Зачем? Разве при добавлении ссылка на коллекцию изменяется?
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
    }

}
