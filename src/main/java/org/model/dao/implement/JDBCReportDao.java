package org.model.dao.implement;

import org.model.dao.DaoFactory;
import org.model.dao.ReportDao;
import org.model.entity.Report;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

public class JDBCReportDao  implements ReportDao {
    private Connection connection;

    public JDBCReportDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void setReportAsNotAssessed(Long selectedReportId)
            throws SQLException {
        connection.setAutoCommit(false);
        try (Statement st = connection.createStatement())  {
            st.addBatch(String.format(DaoFactory.getQuery(
                    "set.report.as.not.assessed"), selectedReportId));
            st.addBatch(String.format(DaoFactory.getQuery(
                    "set.alteration.report.as.assessed"),
                    LocalDateTime.now().format(ISO_LOCAL_DATE_TIME),
                    selectedReportId));
            st.executeBatch();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }
        connection.commit();
        connection.setAutoCommit(true);
    }

    @Override
    public void createReportAlternation(Long selectedReportId,
                                        String reportReclamation,
                                        Long officerID) throws SQLException {
        connection.setAutoCommit(false);
        try (Statement st = connection.createStatement())  {
            st.addBatch(String.format(DaoFactory.getQuery(
                    "create.report.alternation"),
                    LocalDateTime.now().format(ISO_LOCAL_DATE_TIME),
                    reportReclamation, selectedReportId));
            st.addBatch(String.format(DaoFactory.getQuery(
                    "set.report.as.assessed"),
                    officerID, selectedReportId));
            st.executeBatch();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }
        connection.commit();
        connection.setAutoCommit(true);
    }

    @Override
    public void acceptReport(Long selectedReportId, Long officerID) {
        try (PreparedStatement ps = connection.prepareStatement(
                DaoFactory.getQuery("accept.report"))) {
            ps.setLong(1, officerID);
            ps.setString(2, LocalDateTime.now().format(ISO_LOCAL_DATE_TIME));
            ps.setLong(3, selectedReportId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addNewReport(Long payerID, Long officerID) {
        try (PreparedStatement ps = connection.prepareStatement(
                DaoFactory.getQuery("add.new.report"))) {
            ps.setString(1, LocalDateTime.now().format(ISO_LOCAL_DATE_TIME));
            ps.setLong(2, payerID);
            ps.setLong(3, officerID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Report> getNotAcceptedReportsForOfficerLogin(String login) {
        List<Report> rl = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                DaoFactory.getQuery("get.not.accepted.reports.for.officer.login"))) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rl.add(loadReport(rs));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return rl;
    }

    @Override
    public List<Report> getNotAcceptedReportsForPayerLogin(String login) {
        List<Report> rl = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                DaoFactory.getQuery("get.not.accepted.reports.for.payer.login"))) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rl.add(loadReport(rs));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return rl;
    }

    private Report loadReport(ResultSet rs) throws SQLException {
        return Report.builder()
                .setId(rs.getLong("id"))
                .setAccepted(rs.getBoolean("accepted"))
                .setAssessed(rs.getBoolean("assessed"))
                .setCreationTime(rs.getString("creation_time"))
                .setAcceptTime(rs.getString("accept_time"))
                .setPayerID(rs.getLong("taxpayer"))
                .setOfficerID(rs.getLong("taxofficer"))
                .setPayerName(rs.getString("name"))
                .setNote(rs.getString("note"))
                .build();
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
