package org.model.service;

import org.model.dao.DaoFactory;
import org.model.dao.OfficerDao;
import org.model.dao.PayerDao;
import org.model.dao.ReportDao;
import org.model.entity.Report;

import java.util.List;

import static org.model.service.ServiceUtil.createPayerWithForm;

public class Service {
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

    public void setPayerReports(String name) {
        if (reportDao == null) {
            reportDao = DaoFactory.getInstance().createReportDao();
        }
        this.report = reportDao.getNotAcceptedReportsForPayerLogin(name);
    }

    public List<Report> getOfficerReports() {
        return report;
    }

    public void setOfficerReports(String name) {
        if (reportDao == null) {
            reportDao = DaoFactory.getInstance().createReportDao();
        }
        this.report = reportDao
                .getNotAcceptedReportsForOfficerLogin(name);
    }

    public boolean IsOfficerAuthorizedUser(String login, String password) {
        if (officerDao == null) {
            officerDao = DaoFactory.getInstance().createOfficerDao();
        }
        return officerDao.matchForLoginAndPassword(login, password);
    }

    public boolean IsPayerAuthorizedUser(String login, String password) {
        if (payerDao == null) {
            payerDao = DaoFactory.getInstance().createPayerDao();
        }
        return payerDao.matchForLoginAndPassword(login, password);
    }

    public boolean IsNotPayerAuthorizedUser(String login) {
        if (payerDao == null) {
            payerDao = DaoFactory.getInstance().createPayerDao();
        }
        return payerDao.notMatchForLogin(login);
    }

    public void addNewPayer(String login, String name, String password) {
        if (payerDao == null) {
            payerDao = DaoFactory.getInstance().createPayerDao();
        }
        payerDao.addNewPayer(createPayerWithForm(login, name, password));
    }

    public void addNewReport(String login) {
        if (reportDao == null) {
            reportDao = DaoFactory.getInstance().createReportDao();
        }
        if (payerDao == null) {
            payerDao = DaoFactory.getInstance().createPayerDao();
        }
        reportDao.addNewReport(payerDao.getPayerIdByLogin(login),
                payerDao.getOfficerIdByPayerID(login));
    }

    public void acceptReport(String selectedReport, String login) {
        if (reportDao == null) {
            reportDao = DaoFactory.getInstance().createReportDao();
        }
        if (officerDao == null) {
            officerDao = DaoFactory.getInstance().createOfficerDao();
        }
        reportDao.acceptReport(selectedReport, officerDao.getOfficerIdByLogin(login));
    }

}
