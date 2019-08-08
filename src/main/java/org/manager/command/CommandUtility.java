package org.manager.command;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

import static org.Constants.*;

/**
 * Provides utility methods for servlet commands
 */
public class CommandUtility {

    private CommandUtility() {
    }

    public static CommandUtility getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static CommandUtility INSTANCE = new CommandUtility();
    }

    /**
     * Provides adding of user's name and role to session context
     * @param request the request object,
     * @param role the user's role,
     * @param name the user's name
     */
    public void setUserAndRole(HttpServletRequest request,
                            String role, String name) {
        request.getSession().setAttribute(USER_NAME_PARAMETER, name);
        request.getSession().setAttribute(USER_ROLE_PARAMETER, role);
    }

    /**
     * Provides removing of user's name and role records from session context
     * @param request the request object
     */
    public void deleteUserAndRole(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_NAME_PARAMETER);
        request.getSession().removeAttribute(USER_ROLE_PARAMETER);
    }

    /**
     * Provides checking of case when user is already registered
     * @param request the request object,
     * @param userName the user's name
     * @return true for logged user exist case
     */
    public boolean isUserLogged(HttpServletRequest request, String userName) {
        Set<String> loggedUsers = (Set<String>) request.getSession()
                .getServletContext().getAttribute(LOGGED_USERS_PARAMETER);
        return loggedUsers.stream().anyMatch(userName::equals);
    }

    /**
     * Provides adding user's name to users' registration list.
     * @param request the request object,
     * @param userName the user's name
     */
    public void addToLoggedUsers(HttpServletRequest request, String userName) {
        Set<String> loggedUsers = (Set<String>) request.getSession()
                .getServletContext().getAttribute(LOGGED_USERS_PARAMETER);
        loggedUsers.add(userName);
    }

    /**
     * Provides removing of user's registration record from users' registration list
     * @param request the request object
     */
    public void removeFromLoggedUsers(HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute(USER_NAME_PARAMETER);
        Set<String> loggedUsers = (Set<String>) request.getSession()
                .getServletContext().getAttribute(LOGGED_USERS_PARAMETER);
        loggedUsers.remove(userName);
        request.getSession().removeAttribute(userName);
    }

    /**
     * Setting sign of user's mistake during input of authorization data
     * @param request the request object
     */
    public void setInputMistakeSign(HttpServletRequest request) {
        request.getSession().setAttribute(INPUT_MISTAKE_SIGN, EMPTY_STRING);
    }

    /**
     * Remove sign of user's mistake during input of authorization data
     * @param request the request object
     */
    public void removeInputMistakeSign(HttpServletRequest request) {
        request.getSession().removeAttribute(INPUT_MISTAKE_SIGN);
    }

    /**
     * Form request URI for redirect back
     * @param request
     */
    public String getURIForRequestPage(HttpServletRequest request) {
        return request.getRequestURI()
                .replaceAll(request.getRequestURI()
                                .substring(request.getRequestURI()
                                        .lastIndexOf(SEPARATOR)),
                            EMPTY_STRING);
    }

}
