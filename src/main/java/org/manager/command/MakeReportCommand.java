package org.manager.command;

import org.model.UserRole;
import org.model.service.Service;

import javax.servlet.http.HttpServletRequest;

import static org.Constants.*;
import static org.manager.command.CommandUtility.getURIForRequestPage;

public class MakeReportCommand implements Command {

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
            System.out.println("Создание нового отчета");
            Service.getInstance().addNewReport(login);
        } else if (UserRole.PAYER.toString().equals(person_role)
                && (createComplaint != null)) {
            System.out.println("Создание заявки на смену инспектора");
            Service.getInstance().createComplaint(login);
        } else if (UserRole.PAYER.toString().equals(person_role)
                && (editReportByPayer != null)) {
            System.out.println("Внесение изменений в существующий отчет");
            Service.getInstance().editReportByPayer(selectedReport);
        } else if (UserRole.OFFICER.toString().equals(person_role)
                && (acceptReport != null)) {
            System.out.println("Принятие отчета");
            Service.getInstance().acceptReport(selectedReport, login);
        } else if (UserRole.OFFICER.toString().equals(person_role)
                && (reportReclamation != null)) {
            System.out.println("Подготовка рекламации на отчет");
            Service.getInstance().createReportAlternation(selectedReport,
                    reportReclamationText, login);
        } else {
            System.out.println(UNKNOWN_COMMAND);
        }

        return getURIForRequestPage(request);
    }
}