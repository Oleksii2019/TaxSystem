package org.manager.command;

import org.model.UserRole;
import org.model.dao.DaoFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.Constants.*;

public class LogInCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter(LOGIN_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);
        setInputMistakeSign(request);

        if ((name == null) || name.isEmpty()
                || (password == null) || password.isEmpty()
                || !request.getSession().getAttribute(USER_NAME_PARAMETER)
                   .equals(GUEST_USER_NAME)
                || CommandUtility.IsUserLogged(request, name)) {
            return getURIForRequestPage(request);
        }
//        System.out.println(name + " " + password);
//todo: check login with DB

        if (name.equals("Admin")){
            CommandUtility.addToLoggedUsers(request, name);
            CommandUtility.setUserAndRole(request,
                    UserRole.OFFICER.toString(), name);
            removeInputMistakeSign(request);
            return "/tax_system/officer-reports";
        } else if(DaoFactory.getInstance().createPayerDao()
                .matchForLoginAndPassword(name, password)) {
            CommandUtility.addToLoggedUsers(request, name);
            CommandUtility.setUserAndRole(request,
                    UserRole.PAYER.toString(), name);
            removeInputMistakeSign(request);
            return "/tax_system/payer-reports";
        } else {
//            CommandUtility.setUserRole(request, "unknownRole", name);
            return getURIForRequestPage(request);
        }

    }

    /**
     * Setting sign of user's mistake during input of authorization data
     * @param request
     */
    private void setInputMistakeSign(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(INPUT_MISTAKE_SIGN, EMPTY_STRING);
    }

    /**
     * Remove sign of user's mistake during input of authorization data
     * @param request
     */
    private void removeInputMistakeSign(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(INPUT_MISTAKE_SIGN);
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
}
