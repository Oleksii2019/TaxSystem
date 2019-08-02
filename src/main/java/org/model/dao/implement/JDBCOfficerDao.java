package org.model.dao.implement;

import org.apache.log4j.Logger;
import org.model.dao.DaoFactory;
import org.model.dao.OfficerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCOfficerDao implements OfficerDao {
    private static final Logger LOGGER =
            Logger.getLogger(JDBCOfficerDao.class);
    private Connection connection;

    public JDBCOfficerDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Long getOfficerIdByLogin(String officerLogin) {
        long res = 0L;
        try (PreparedStatement ps = connection.prepareStatement(
                DaoFactory.getQuery("get.officer.id.by.login"))) {
            ps.setString(1, officerLogin);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res = rs.getLong("id");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return res;
    }

    @Override
    public boolean matchForLoginAndPassword(String login, String password) {
        boolean result = false;
        try (PreparedStatement ps = connection.prepareStatement(
                DaoFactory.getQuery("match.for.officer.login.and.password"))) {
            ps.setString(1, login);
            ps.setString(2, password);
            if (ps.executeQuery().next()) {
                result = true;
            }
        } catch(SQLException e) {
            LOGGER.error(e);
        }
        return result;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch(SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
    }

}
