package org.model.dao.implement;

import org.model.dao.OfficerDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCOfficerDao implements OfficerDao {
    private Connection connection;

    public JDBCOfficerDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean matchForLoginAndPassword(String login, String password) {
        boolean result = false;

        final String query = "select * from taxofficers where login = "
                + "\"" + login + "\"" + " and password = "
                + "\"" + password + "\"";
        try (Statement st = connection.createStatement()) {
            if (st.executeQuery(query).next()) {
                result = true;
            }
        } catch(SQLException e) {
            // TODO SQLException
            e.printStackTrace();
        }
        return result;
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
