package org.manager.listeners;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashSet;

import static org.Constants.LOGGED_USERS_PARAMETER;
import static org.Constants.USER_NAME_PARAMETER;

public class SessionListener  implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HashSet<String> loggedUsers = (HashSet<String>) httpSessionEvent
                .getSession().getServletContext()
                .getAttribute(LOGGED_USERS_PARAMETER);
        String userName = (String) httpSessionEvent.getSession()
                .getAttribute(USER_NAME_PARAMETER);
        loggedUsers.remove(userName);
//        httpSessionEvent.getSession().setAttribute(LOGGED_USERS_PARAMETER, loggedUsers);
    }


}
