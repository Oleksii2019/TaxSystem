package org.model.dao;

import org.model.entity.Report;

import java.sql.SQLException;
import java.util.List;

public interface ReportDao extends GenericDao<Report>  {
    List<Report> getNotAcceptedReportsForOfficerLogin(String login);
    List<Report> getNotAcceptedReportsForPayerLogin(String login);
    void addNewReport(Long payerID, Long officerID);
    void acceptReport(Long selectedReportId, Long officerID);
    void createReportAlternation(Long selectedReportId,
                                 String reportReclamation,
                                 Long officerID) throws SQLException;
    void setReportAsNotAssessed(Long selectedReportId) throws SQLException;
}
