package org.model.dao.implement;

import org.model.dao.PayerDao;
import org.model.entity.Payer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCPayerDao implements PayerDao {
    private Connection connection;

    public JDBCPayerDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Payer> getNotAcceptedReportsForOfficerLogin(String login) {
        return new ArrayList<>();
    }

    @Override
    public List<Payer> getNotAcceptedReportsForPayerLogin(String login) {
        return new ArrayList<>();
    }

    @Override
    public boolean matchForLoginAndPassword(String login, String password) {
        boolean result = false;

        final String query = "select * from taxpayers where login = "
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
