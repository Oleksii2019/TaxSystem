package org.manager.command;

import org.apache.log4j.Logger;
import org.model.UserRole;
import org.model.service.Service;

import javax.servlet.http.HttpServletRequest;

import static org.Constants.*;

public class MakeReportCommand implements Command {
    private static final Logger LOGGER =
            Logger.getLogger(MakeReportCommand.class);
    private CommandUtility commandUtility;
    private Service service;

    public MakeReportCommand() {
        this(CommandUtility.getInstance(),
                Service.getInstance());
    }
    private MakeReportCommand(CommandUtility commandUtility,
                         Service service) {
        this.commandUtility = commandUtility;
        this.service = service;
    }


    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getSession()
                .getAttribute(USER_NAME_PARAMETER).toString();
        String person_role = request.getSession()
                .getAttribute(USER_ROLE_PARAMETER).toString();
        String createPayerReport = request.getParameter(
                CREATE_PAYER_REPORT_PARAMETER);
        String createComplaint = request.getParameter(
                CREATE_COMPLAINT_PARAMETER);
        String editReportByPayer = request.getParameter(
                EDIT_REPORT_PARAMETER);
        String acceptReport = request.getParameter(
                ACCEPT_REPORT_PARAMETER);
        String reportReclamation = request.getParameter(
                REPORT_RECLAMATION_PARAMETER);
        String reportReclamationText = request.getParameter(
                REPORT_RECLAMATION_TEXT_PARAMETER);
        String selectedReport = request.getParameter(
                SELECTED_REPORT_PARAMETER);

        if (UserRole.PAYER.toString().equals(person_role)
                && (createPayerReport != null)) {
            LOGGER.info(CREATE_NEW_REPORT);
            service.addNewReport(login);
        } else if (UserRole.PAYER.toString().equals(person_role)
                && (createComplaint != null)) {
            LOGGER.info(CREATE_COMPLAINT);
            service.createComplaint(login);
        } else if (UserRole.PAYER.toString().equals(person_role)
                && (editReportByPayer != null)) {
            LOGGER.info(EDIT_REPORT);
            service.editReportByPayer(selectedReport);
        } else if (UserRole.OFFICER.toString().equals(person_role)
                && (acceptReport != null)) {
            LOGGER.info(ACCEPT_REPORT);
            service.acceptReport(selectedReport, login);
        } else if (UserRole.OFFICER.toString().equals(person_role)
                && (reportReclamation != null)) {
            LOGGER.info(CREATE_ALTERNATE_REPORT);
            service.createReportAlternation(selectedReport,
                    reportReclamationText, login);
        } else {
            LOGGER.info(UNKNOWN_COMMAND);
        }

        return commandUtility.getURIForRequestPage(request);
    }
}