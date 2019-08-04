package org.manager.command;

import org.TableOfURI;
import org.apache.log4j.Logger;
import org.model.UserRole;
import org.model.service.Service;

import javax.servlet.http.HttpServletRequest;

import static org.Constants.*;

public class LogInCommand implements Command {
    private static final Logger LOGGER =
            Logger.getLogger(LogInCommand.class);
    private CommandUtility commandUtility;
    private Service service;

    public LogInCommand() {
        this(CommandUtility.getInstance(),
             Service.getInstance());
    }
    private LogInCommand(CommandUtility commandUtility,
                        Service service) {
        this.commandUtility = commandUtility;
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(LOGIN_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);
        String person_role = request.getParameter(USER_ROLE_PARAMETER);
        commandUtility.setInputMistakeSign(request);
//        CommandUtility.getInstance().setInputMistakeSign(request);

        if ((login == null) || login.isEmpty()
                || (password == null) || password.isEmpty()
                || !request.getSession().getAttribute(USER_NAME_PARAMETER)
                   .equals(GUEST_USER_NAME)
                ||  commandUtility
                   .isUserLogged(request, login)) {
            return  commandUtility.getURIForRequestPage(request);
        }

        if (UserRole.OFFICER.toString().equals(person_role)
                && service.isOfficerAuthorizedUser(
                        login, password)){
            userRegistrationInApp(request, login, person_role);
            LOGGER.info(COMPLETE_LOGIN + login);
            return TableOfURI.OFFICER_REPORTS.getPagePath();
        } else if(UserRole.PAYER.toString().equals(person_role)
                && service.isPayerAuthorizedUser(
                        login, password)) {
            userRegistrationInApp(request, login, person_role);
            LOGGER.info(COMPLETE_LOGIN + login);
            return TableOfURI.PAYER_REPORTS.getPagePath();
        } else {
            return  commandUtility.getURIForRequestPage(request);
        }
    }

    private void userRegistrationInApp(HttpServletRequest request,
                                       String login,
                                       String userRole) {
        commandUtility.addToLoggedUsers(request, login);
        commandUtility.setUserAndRole(request, userRole, login);
        commandUtility.removeInputMistakeSign(request);
    }
}
