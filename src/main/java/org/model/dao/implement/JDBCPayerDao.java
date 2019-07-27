package org.model.dao.implement;

import org.model.dao.PayerDao;
import org.model.entity.Payer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCPayerDao implements PayerDao {
    private Connection connection;

    public JDBCPayerDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Long getOfficerIdByPayerID(String payerLogin) {
        Long res = 0L;
        final String query =
                "select taxofficer from taxpayers where login = \""
                        + payerLogin + "\";";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                res = rs.getLong("taxofficer");
            }
        } catch (SQLException e) {
            // TODO SQLException
            e.printStackTrace();
        }
        return res;
    }


    @Override
    public Long getPayerIdByLogin(String payerLogin) {
        Long res = 0L;
        final String query =
                "select id from taxpayers where login = \""
                + payerLogin + "\";";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                res = rs.getLong("id");
            }
        } catch (SQLException e) {
            // TODO SQLException
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public void addNewPayer(Payer payer) {
//       insert into taxpayers (login, name, password, role, taxofficer)
//       value ("Nike3", "Nike3 Payer", "22", 1, 2);

        final String query =
                "insert into taxpayers (login, name, password, role, "
                        + "taxofficer) value (\"" + payer.getLogin() + "\", \""
                        + payer.getName() + "\", \"" + payer.getPassword()
                        + "\", " + payer.getRole() + ", "
                        + payer.getOfficerID() + ");";
        try (Statement st = connection.createStatement()) {
            if (st.executeUpdate(query)==0) {
                // TODO SQLException, writing not execute
                System.out.println("writing to DB not execute");
            }
        } catch (SQLException e) {
            // TODO SQLException
            e.printStackTrace();
        }
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
    public boolean notMatchForLogin(String login) {
        boolean result = true;

        final String query = "select * from taxpayers where login = "
                + "\"" + login + "\"";
        try (Statement st = connection.createStatement()) {
            if (st.executeQuery(query).next()) {
                result = false;
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
