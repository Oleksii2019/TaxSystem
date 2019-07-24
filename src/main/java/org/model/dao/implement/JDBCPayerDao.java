package org.model.dao.implement;

import org.model.dao.PayerDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCPayerDao implements PayerDao {
    private Connection connection;

    public JDBCPayerDao(Connection connection) {
        this.connection = connection;
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

            //            Payer p = new Payer();
//            while (rs.next()) {
//                p.setLogin(rs.getString("login"));
//                p.setName(rs.getString("name"));
//            }


        } catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch(SQLException e) {
            throw new RuntimeException(e + " - ошибка отключения БД");
        }
    }

}
