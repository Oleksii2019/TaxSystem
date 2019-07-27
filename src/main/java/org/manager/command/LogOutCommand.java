package org.manager.command;

import org.TableOfURI;
import org.model.UserRole;

import javax.servlet.http.HttpServletRequest;

import static org.Constants.*;

public class LogOutCommand implements Command  {

    @Override
    public String execute(HttpServletRequest request) {
        CommandUtility.removeFromLoggedUsers(request);
        CommandUtility.deleteUserAndRole(request);
        request.getSession().setAttribute(USER_NAME_PARAMETER, GUEST_USER_NAME);
        request.getSession().setAttribute(USER_ROLE_PARAMETER, UserRole.GUEST.toString());
        return TableOfURI.HOME.getPagePath();
    }

}
