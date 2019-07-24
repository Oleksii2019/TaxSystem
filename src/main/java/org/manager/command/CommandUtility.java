package org.manager.command;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

import static org.Constants.*;

public class CommandUtility {

    static void setUserAndRole(HttpServletRequest request,
                            String role, String name) {
        request.getSession().setAttribute(USER_NAME_PARAMETER, name);
        request.getSession().setAttribute(USER_ROLE_PARAMETER, role);
    }

    static void deleteUserAndRole(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_NAME_PARAMETER);
        request.getSession().removeAttribute(USER_ROLE_PARAMETER);
    }

    static boolean IsUserLogged(HttpServletRequest request, String userName) {
        Set<String> loggedUsers = (Set<String>) request.getSession()
                .getServletContext().getAttribute(LOGGED_USERS_PARAMETER);
        return loggedUsers.stream().anyMatch(userName::equals);
    }

    static void addToLoggedUsers(HttpServletRequest request, String userName) {
        Set<String> loggedUsers = (Set<String>) request.getSession()
                .getServletContext().getAttribute(LOGGED_USERS_PARAMETER);
        loggedUsers.add(userName);
    }

    static void removeFromLoggedUsers(HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute(USER_NAME_PARAMETER);
        Set<String> loggedUsers = (Set<String>) request.getSession()
                .getServletContext().getAttribute(LOGGED_USERS_PARAMETER);
        loggedUsers.remove(userName);
        request.getSession().removeAttribute(userName);
    }

}
