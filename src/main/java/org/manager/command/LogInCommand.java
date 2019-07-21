package org.manager.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.Constants.*;

public class LogInCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("login");
        String password = request.getParameter("password");
        setInputMistakeSign(request);

        if ((name == null) || name.isEmpty()
                || (password == null) || password.isEmpty()
                || CommandUtility.IsUserLogged(request, name)) {
            return getURIForRequestPage(request);
        }
//        System.out.println(name + " " + password);
//todo: check login with DB

        if (name.equals("Admin")){
//            CommandUtility.addToLoggedUsers(request, name);
//            CommandUtility.setUserRole(request, "adminRole", name);
            removeInputMistakeSign(request);
            return "/tax_system/payer-reports";
        } else if(name.equals("User")) {
//            CommandUtility.setUserRole(request, "userRole", name);
//            removeInputMistakeSign(request);
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
                            "");
    }
}
