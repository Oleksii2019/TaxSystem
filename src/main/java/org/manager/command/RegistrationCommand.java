package org.manager.command;

import org.TableOfURI;
import org.apache.log4j.Logger;
import org.model.service.Service;

import javax.servlet.http.HttpServletRequest;

import static org.Constants.*;

public class RegistrationCommand implements Command {
    private static final Logger LOGGER =
            Logger.getLogger(RegistrationCommand.class);
    private CommandUtility commandUtility;
    private Service service;

    public RegistrationCommand() {
        this(CommandUtility.getInstance(),
             Service.getInstance());
    }
    private RegistrationCommand(CommandUtility commandUtility,
                                Service service) {
        this.commandUtility = commandUtility;
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(LOGIN_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);
        String regName = request.getParameter(REG_NAME_PARAMETER);
        commandUtility.setInputMistakeSign(request);

        if ((login == null) || login.isEmpty()
                || (password == null) || password.isEmpty()
                || (regName == null) || regName.isEmpty()
                || !request.getSession().getAttribute(USER_NAME_PARAMETER)
                .equals(GUEST_USER_NAME)) {
            return commandUtility.getURIForRequestPage(request);
        }

        if (service.isNotPayerAuthorizedUser(login)) {
            commandUtility.removeInputMistakeSign(request);
            service.addNewPayer(login, regName, password);
            LOGGER.info(COMPLETE_REGISTRATION + login);
            return TableOfURI.HOME.getPagePath();
        } else {
            return commandUtility.getURIForRequestPage(request);
        }
    }

}