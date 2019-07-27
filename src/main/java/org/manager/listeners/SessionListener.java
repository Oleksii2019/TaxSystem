package org.manager.listeners;

import org.model.UserRole;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Set;

import static org.Constants.*;

public class SessionListener  implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        event.getSession().setAttribute(LANGUAGE_PARAMETER,
                                        Localization.UK.toString());
        event.getSession().setAttribute(USER_NAME_PARAMETER,
                                        GUEST_USER_NAME);
        event.getSession().setAttribute(USER_ROLE_PARAMETER,
                                        UserRole.GUEST.toString());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        Set<String> loggedUsers = (Set<String>) httpSessionEvent
                .getSession().getServletContext()
                .getAttribute(LOGGED_USERS_PARAMETER);
        String userName = (String) httpSessionEvent.getSession()
                .getAttribute(USER_NAME_PARAMETER);
        loggedUsers.remove(userName);
    }

}
