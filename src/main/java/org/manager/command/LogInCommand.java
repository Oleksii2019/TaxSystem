package org.manager.command;

import org.TableOfURI;
import org.apache.log4j.Logger;
import org.model.UserRole;
import org.model.service.Service;

import javax.servlet.http.HttpServletRequest;

import static org.Constants.*;
import static org.manager.command.CommandUtility.*;

public class LogInCommand implements Command {
    private static final Logger LOGGER =
            Logger.getLogger(LogInCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(LOGIN_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);
        String person_role = request.getParameter(USER_ROLE_PARAMETER);
        setInputMistakeSign(request);

        if ((login == null) || login.isEmpty()
                || (password == null) || password.isEmpty()
                || !request.getSession().getAttribute(USER_NAME_PARAMETER)
                   .equals(GUEST_USER_NAME)
                || CommandUtility.IsUserLogged(request, login)) {
            return getURIForRequestPage(request);
        }

        if (UserRole.OFFICER.toString().equals(person_role)
                && Service.getInstance().IsOfficerAuthorizedUser(
                        login, password)){
            UserRegistrationInApp(request, login, person_role);
            LOGGER.info(COMPLETE_LOGIN + login);
            return TableOfURI.OFFICER_REPORTS.getPagePath();
        } else if(UserRole.PAYER.toString().equals(person_role)
                && Service.getInstance().IsPayerAuthorizedUser(
                        login, password)) {
            UserRegistrationInApp(request, login, person_role);
            LOGGER.info(COMPLETE_LOGIN + login);
            return TableOfURI.PAYER_REPORTS.getPagePath();
        } else {
            return getURIForRequestPage(request);
        }
    }

    private void UserRegistrationInApp(HttpServletRequest request,
                                       String login,
                                       String userRole) {
        CommandUtility.addToLoggedUsers(request, login);
        CommandUtility.setUserAndRole(request, userRole, login);
        removeInputMistakeSign(request);
    }
}
