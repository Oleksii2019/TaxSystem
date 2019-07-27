package org.manager.command;

import org.TableOfURI;
import org.model.service.Service;

import javax.servlet.http.HttpServletRequest;

import static org.Constants.*;
import static org.manager.command.CommandUtility.*;

public class RegistrationCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(LOGIN_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);
        String regName = request.getParameter(REG_NAME_PARAMETER);
//        String person_role = request.getParameter(USER_ROLE_PARAMETER);
        setInputMistakeSign(request);

        if ((login == null) || login.isEmpty()
                || (password == null) || password.isEmpty()
                || (regName == null) || regName.isEmpty()
                || !request.getSession().getAttribute(USER_NAME_PARAMETER)
                .equals(GUEST_USER_NAME)) {
            return getURIForRequestPage(request);
        }

        if (Service.getInstance().IsNotPayerAuthorizedUser(login)){
            removeInputMistakeSign(request);
            System.out.println("К регистрации готов!");
            Service.getInstance().addNewPayer(login, regName, password);
            return TableOfURI.HOME.getPagePath();
        } else {
            return getURIForRequestPage(request);
        }

    }

}