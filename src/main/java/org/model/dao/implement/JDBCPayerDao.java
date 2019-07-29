package org.model.dao.implement;

import org.model.dao.PayerDao;
import org.model.entity.Payer;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import static org.model.dao.implement.JDBCUtil.*;

public class JDBCPayerDao implements PayerDao {
    private Connection connection;

    public JDBCPayerDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createComplaint(Long payerID, Long officerID) {
        insertRow("insert into replacement_request (creation_time, "
                + "taxofficer, taxpayer) value (\""
                + LocalDateTime.now().format(ISO_LOCAL_DATE_TIME)
                + "\", " + officerID + ", " + payerID
                + ");",
                connection
        );
    }

    @Override
    public boolean isNotComplaintExist(Long payerID, Long officerID) {
        return checkNotMatch("select * from replacement_request "
                + "where taxpayer = " + payerID + " and taxofficer = "
                + officerID + ";",
                connection
        );
    }

    @Override
    public Long getOfficerIdByPayerID(String payerLogin) {
        return getLong(
                "select taxofficer from taxpayers where login = \""
                        + payerLogin + "\";", "taxofficer",
                connection
        );
    }

    @Override
    public Long getPayerIdByLogin(String payerLogin) {
        return getLong("select id from taxpayers where login = \""
                        + payerLogin + "\";", "id",
                connection
        );
    }

    @Override
    public void addNewPayer(Payer payer) {
        insertRow("insert into taxpayers (login, name, password, role, "
                  + "taxofficer) value (\"" + payer.getLogin() + "\", \""
                  + payer.getName() + "\", \"" + payer.getPassword()
                  + "\", " + payer.getRole() + ", "
                  + payer.getOfficerID() + ");",
                connection
        );
    }

    @Override
    public boolean matchForLoginAndPassword(String login, String password) {
        return checkMatch("select * from taxpayers where login = "
                + "\"" + login + "\"" + " and password = "
                + "\"" + password + "\"",
                connection
        );
    }

    @Override
    public boolean notMatchForLogin(String login) {
        return checkNotMatch("select * from taxpayers where login = "
                + "\"" + login + "\"",
                connection
        );
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

}
