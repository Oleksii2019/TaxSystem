package org.model.dao.implement;

import org.model.dao.ReportDao;
import org.model.entity.Report;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import static org.Constants.DATE_TIME_FORMAT_PATTERN;

public class JDBCReportDao  implements ReportDao {
    private Connection connection;

    public JDBCReportDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void setReportAsNotAssessed(Long selectedReportId) {
        final String query =
                "update reports set assessed = false "
                 + "where id = " + selectedReportId + ";";
        makeUpdate(query);
    }

    @Override
    public void setAlternatReportAsAccepted(Long selectedReportId) {
        final String query =
                "update report_alteration set accepted = true, "
                + "accept_time = \""
                + LocalDateTime.now().format(ISO_LOCAL_DATE_TIME)
                + "\" where report = " + selectedReportId
                + " and accepted = false;";
        makeUpdate(query);
    }

    @Override
    public void setPayerReportAsAssessed(Long selectedReportId,
                                         Long officerID) {
        final String query =
                "update reports set assessed = true, "
                + "taxofficer = " + officerID
                + " where id = " + selectedReportId + ";";
        makeUpdate(query);
//        try (Statement st = connection.createStatement()) {
//            if (st.executeUpdate(query) == 0) {
//                // TODO SQLException, writing not execute
//                System.out.println("updating DB not execute");
//            }
//        } catch (SQLException e) {
//            // TODO SQLException
//            e.printStackTrace();
//        }
    }

    @Override
    public void createReportAlternation(Long selectedReportId,
                                     String reportReclamation) {
        final String query =
                "insert into report_alteration (accept_time, accepted, "
                + "creation_time, note, report) value (null, "
                + "false, \""
                + LocalDateTime.now().format(ISO_LOCAL_DATE_TIME)
                + "\", \"" + reportReclamation + "\", "
                + selectedReportId + ");";
        makeUpdate(query);
//        try (Statement st = connection.createStatement()) {
//            if (st.executeUpdate(query) == 0) {
//                // TODO SQLException, writing not execute
//                System.out.println("writing to DB not execute");
//            }
//        } catch (SQLException e) {
//            // TODO SQLException
//            e.printStackTrace();
//        }
    }

    @Override
    public void acceptReport(Long selectedReportId, Long officerID) {

        final String query =
                "update reports set accepted = true, assessed = true, "
                + "taxofficer = " + officerID + ", accept_time = \""
                + LocalDateTime.now().format(ISO_LOCAL_DATE_TIME)
//                        LocalDateTime.now().format(DateTimeFormatter
//                        .ofPattern(DATE_TIME_FORMAT_PATTERN))
                + "\" where id = " + selectedReportId + ";";
        makeUpdate(query);
//        try (Statement st = connection.createStatement()) {
//            if (st.executeUpdate(query) == 0) {
//                // TODO SQLException, writing not execute
//                System.out.println("updating DB not execute");
//            }
//        } catch (SQLException e) {
//            // TODO SQLException
//            e.printStackTrace();
//        }
    }

    @Override
    public void addNewReport(Long payerID, Long officerID) {

//        + LocalDateTime.now().format(DateTimeFormatter
//                .ofPattern(DATE_TIME_FORMAT_PATTERN))

//         LocalDateTime.now().format(ISO_LOCAL_DATE_TIME)

        final String query =
                "insert into reports (accept_time, accepted, assessed, "
                + "creation_time, taxpayer, taxofficer) value (null, "
                + "false, false, \""
                + LocalDateTime.now().format(ISO_LOCAL_DATE_TIME)
                + "\", " + payerID + ", " + officerID + ");";
        makeUpdate(query);
//        try (Statement st = connection.createStatement()) {
//            if (st.executeUpdate(query) == 0) {
//                // TODO SQLException, writing not execute
//                System.out.println("writing to DB not execute");
//            }
//        } catch (SQLException e) {
//            // TODO SQLException
//            e.printStackTrace();
//        }
    }


    @Override
    public List<Report> getNotAcceptedReportsForOfficerLogin(String login) {

        return loadDataWithConnection(
                "select reports.*, taxpayers.name, "
                + "report_alteration.note from reports "
                + "join taxpayers on reports.taxpayer = taxpayers.id "
                + "join taxofficers on reports.taxofficer = taxofficers.id "
                + "or taxofficers.id = taxpayers.taxofficer "
                + "left join report_alteration on report_alteration.report "
                + "= reports.id and report_alteration.accepted = false "
                + "where taxofficers.login = \"" + login
                + "\" and reports.assessed = false and reports.accepted = "
                + "false;"
        );
    }

    @Override
    public List<Report> getNotAcceptedReportsForPayerLogin(String login) {

        return loadDataWithConnection(
                "select reports.*, taxpayers.name, "
                + "report_alteration.note from reports "
//              + "join taxofficers on reports.taxofficer = taxofficers.id "
                + "join taxpayers on reports.taxpayer = taxpayers.id "
                + "left join report_alteration on report_alteration.report "
                + "= reports.id and report_alteration.accepted = false "
                + "where taxpayers.login = \"" + login + "\" and "
                + "reports.accepted = false;"
        );
    }

    private List<Report> loadDataWithConnection(String query) {
        List<Report> rl = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                rl.add(Report.builder()
                        .setId(rs.getLong("id"))
                        .setAccepted(rs.getBoolean("accepted"))
                        .setAssessed(rs.getBoolean("assessed"))
                        .setCreationTime(rs.getString("creation_time"))
                        .setAcceptTime(rs.getString("accept_time"))
                        .setPayerID(rs.getLong("taxpayer"))
                        .setOfficerID(rs.getLong("taxofficer"))
                        .setPayerName(rs.getString("name"))
                        .setNote(rs.getString("note"))
                        .build()
                );
            }
        } catch(SQLException e) {
            // TODO SQLException
            e.printStackTrace();
        }
        return rl;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch(SQLException e) {
            // TODO SQLException
            throw new RuntimeException(e + " - ошибка отключения БД");
        }
    }


    // Метод преобразует время, полученное из БД как строка, в формат, к-ый использовался в БД при записи
    public LocalDateTime stringToLocalDateTime(String dateTime) {
        if (dateTime == null) {
            return null;
        } else {
            return LocalDateTime.parse(dateTime, DateTimeFormatter
                    .ofPattern(DATE_TIME_FORMAT_PATTERN));
        }
    }

    public void makeUpdate(String query) {
        try (Statement st = connection.createStatement()) {
            if (st.executeUpdate(query) == 0) {
                // TODO SQLException, writing not execute
                System.out.println("writing to DB not execute");
            }
        } catch (SQLException e) {
            // TODO SQLException
            e.printStackTrace();
        }
    }

}
