package org.manager.command;

import org.model.UserRole;
import org.model.dao.DaoFactory;
import org.model.entity.Report;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.Constants.*;

public class LogInCommand implements Command {

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

        List<Report> rl;
        if (UserRole.OFFICER.toString().equals(person_role)
                && DaoFactory.getInstance().createOfficerDao()
                .matchForLoginAndPassword(login, password)){
            UserRegistrationInApp(request, login, person_role);
            rl = DaoFactory.getInstance().createReportDao().getNotAcceptedReportsForOfficerLogin(login);
            System.out.println("Получено записей: " + rl.size());
            return "/tax_system/officer-reports";
        } else if(UserRole.PAYER.toString().equals(person_role)
                && DaoFactory.getInstance().createPayerDao()
                .matchForLoginAndPassword(login, password)) {
            UserRegistrationInApp(request, login, person_role);
            rl = DaoFactory.getInstance().createReportDao().getNotAcceptedReportsForPayerLogin(login);
            System.out.println("Получено записей: " + rl.size());
            return "/tax_system/payer-reports";
        } else {
            return getURIForRequestPage(request);
        }
    }

    /**
     * Setting sign of user's mistake during input of authorization data
     * @param request
     */
    private void setInputMistakeSign(HttpServletRequest request) {
        request.getSession().setAttribute(INPUT_MISTAKE_SIGN, EMPTY_STRING);
    }

    /**
     * Remove sign of user's mistake during input of authorization data
     * @param request
     */
    private void removeInputMistakeSign(HttpServletRequest request) {
        request.getSession().removeAttribute(INPUT_MISTAKE_SIGN);
    }

    /**
     * Form request URI for redirect back
     * @param request
     */
    private String getURIForRequestPage(HttpServletRequest request) {
        return request.getRequestURI()
                .replaceAll(request.getRequestURI()
                                   .substring(request.getRequestURI()
                                   .lastIndexOf(SEPARATOR)),
                            EMPTY_STRING);
    }

    private void UserRegistrationInApp(HttpServletRequest request,
                                       String login,
                                       String userRole) {
        CommandUtility.addToLoggedUsers(request, login);
        CommandUtility.setUserAndRole(request, userRole, login);
        removeInputMistakeSign(request);
    }
}
