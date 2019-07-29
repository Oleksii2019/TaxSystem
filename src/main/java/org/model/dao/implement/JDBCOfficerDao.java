package org.model.dao.implement;

import org.model.dao.OfficerDao;

import java.sql.Connection;
import java.sql.SQLException;

import static org.model.dao.implement.JDBCUtil.checkMatch;
import static org.model.dao.implement.JDBCUtil.getLong;

public class JDBCOfficerDao implements OfficerDao {
    private Connection connection;

    public JDBCOfficerDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long getOfficerIdByLogin(String officerLogin) {
        return getLong(
                "select id from taxofficers where login = \""
                        + officerLogin + "\";", "id",
                connection
        );
    }

    @Override
    public boolean matchForLoginAndPassword(String login, String password) {
        return checkMatch("select * from taxofficers where login = "
                        + "\"" + login + "\"" + " and password = "
                        + "\"" + password + "\"",
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
