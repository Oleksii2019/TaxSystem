package org.model.dao.implement;

import org.model.dao.ReportDao;
import org.model.entity.Report;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.Constants.*;

public class JDBCReportDao  implements ReportDao {
    private Connection connection;

    public JDBCReportDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Report> getNotAcceptedReportsForOfficerLogin(String login) {
        return loadDataWithConnection(
                "select reports.* from reports "
                        + "join taxofficers on reports.taxofficer = taxofficers.id "
                        + "where login =\"" + login + "\" and accepted = false;"
        );
    }

    @Override
    public List<Report> getNotAcceptedReportsForPayerLogin(String login) {
        return loadDataWithConnection(
                "select reports.* from reports "
                        + "join taxpayers on reports.taxpayer = taxpayers.id "
                        + "where login =\"" + login + "\" and accepted = false;"
        );
    }

    private List<Report> loadDataWithConnection(String query) {
        List<Report> rl = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                rl.add(Report.builder()
                        .setAccepted(rs.getBoolean("accepted"))
                        .setAssessed(rs.getBoolean("assessed"))
                        .setCreationTime(stringToLocalDateTime(
                                rs.getString("creation_time")))
                        .setAcceptTime(stringToLocalDateTime(
                                rs.getString("accept_time")))
                        .setPayerID(rs.getLong("taxpayer"))
                        .setOfficerID(rs.getLong("taxofficer"))
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
    public boolean matchForLoginAndPassword(String login, String password) {
        return false;
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


    public LocalDateTime stringToLocalDateTime(String dateTime) {
        if (dateTime == null) {
            return null;
        } else {
            return LocalDateTime.parse(dateTime, DateTimeFormatter
                    .ofPattern(DATE_TIME_FORMAT_PATTERN));
        }
    }

}
