package org.model.service;

import org.apache.log4j.Logger;
import org.model.dao.DaoFactory;
import org.model.dao.OfficerDao;
import org.model.dao.PayerDao;
import org.model.dao.ReportDao;
import org.model.entity.Report;

import java.sql.SQLException;
import java.util.List;

import static org.Constants.*;
import static org.model.service.ServiceUtil.createPayerWithForm;
import static org.model.service.ServiceUtil.getSelectedReportId;

public class Service {
    private static final Logger LOGGER =
            Logger.getLogger(Service.class);
    private static ReportDao reportDao;
    private static OfficerDao officerDao;
    private static PayerDao payerDao;
    private static Service instance;
    private List<Report> report;

    private Service() {
    }

    public static Service getInstance(){
        if (instance == null){
            synchronized (Service.class) {
                if (instance == null) {
                    instance = new Service();
                }
            }
        }
        return instance;
    }

    public List<Report> getPayerReports() {
        return report;
    }

    public List<Report> getOfficerReports() {
        return report;
    }

    public void setPayerReports(String name) {
        initReportDao();
        this.report = reportDao.getNotAcceptedReportsForPayerLogin(name);
    }

    public void setOfficerReports(String name) {
        initReportDao();
        this.report = reportDao
                .getNotAcceptedReportsForOfficerLogin(name);
    }

    public boolean isOfficerAuthorizedUser(String login, String password) {
        initOfficerDao();
        return officerDao.matchForLoginAndPassword(login, password);
    }

    public boolean isPayerAuthorizedUser(String login, String password) {
        initPayerDao();
        return payerDao.matchForLoginAndPassword(login, password);
    }

    public boolean isNotPayerAuthorizedUser(String login) {
        initPayerDao();
        return payerDao.notMatchForLogin(login);
    }

    public void addNewPayer(String login, String name, String password) {
        initPayerDao();
        payerDao.addNewPayer(createPayerWithForm(login, name, password));
    }

    public void addNewReport(String login) {
        initReportDao();
        initPayerDao();
        reportDao.addNewReport(payerDao.getPayerIdByLogin(login),
                payerDao.getOfficerIdByPayerID(login));
    }

    public void acceptReport(String selectedReport, String login) {
        initOfficerDao();
        initReportDao();
        reportDao.acceptReport(getSelectedReportId(report, selectedReport),
                officerDao.getOfficerIdByLogin(login));
    }

    public void createReportAlternation(String selectedReport,
                                        String reportReclamation,
                                        String login) {
        initOfficerDao();
        initReportDao();
        Long reportID = getSelectedReportId(report, selectedReport);
        try {
            reportDao.createReportAlternation(reportID,
                    reportReclamation,
                    officerDao.getOfficerIdByLogin(login));
        } catch(SQLException e) {
            LOGGER.error(EXCEPTION_NESSAGE + e);
        }
    }

    public void editReportByPayer(String selectedReport) {
        initReportDao();
        Long reportID = getSelectedReportId(report, selectedReport);
        if (reportDao == null) {
            reportDao = DaoFactory.getInstance().createReportDao();
        }
        try {
            reportDao.setReportAsNotAssessed(reportID);
        } catch (SQLException e) {
            LOGGER.error(EXCEPTION_NESSAGE + e);
        }
    }

    public void createComplaint(String payerLogin) {
        initPayerDao();
        Long officerID = payerDao.getOfficerIdByPayerID(payerLogin);
        Long  payerID = payerDao.getPayerIdByLogin(payerLogin);
        if (payerDao.isNotComplaintExist(payerID, officerID)) {
            payerDao.createComplaint(payerID, officerID);
        }
    }

    private void initReportDao() {
        if (reportDao == null) {
            reportDao = DaoFactory.getInstance().createReportDao();
        }
    }

    private void initPayerDao() {
        if (payerDao == null) {
            payerDao = DaoFactory.getInstance().createPayerDao();
        }
    }

    private void initOfficerDao() {
        if (officerDao == null) {
            officerDao = DaoFactory.getInstance().createOfficerDao();
        }
    }

}
