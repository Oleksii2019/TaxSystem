package org.model.dao.implement;

import org.apache.log4j.Logger;
import org.model.dao.DaoFactory;
import org.model.dao.PayerDao;
import org.model.entity.Payer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;

public class JDBCPayerDao implements PayerDao {
    private static final Logger LOGGER = Logger.getLogger(JDBCPayerDao.class);

    private Connection connection;

    public JDBCPayerDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createComplaint(Long payerID, Long officerID) {
        try (PreparedStatement ps = connection.prepareStatement(
                DaoFactory.getQuery("create.complaint"))) {
            ps.setString(1, LocalDateTime.now()
                    .format(ISO_LOCAL_DATE_TIME));
            ps.setLong(2, officerID);
            ps.setLong(3, payerID);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public boolean isNotComplaintExist(Long payerID, Long officerID) {
        boolean result = true;
        try (PreparedStatement ps = connection.prepareStatement(
                DaoFactory.getQuery("is.not.complaint.exist"))) {
            ps.setLong(1, payerID);
            ps.setLong(2, officerID);
            if (ps.executeQuery().next()) {
                result = false;
            }
        } catch(SQLException e) {
            LOGGER.error(e);
        }
        return result;
    }

    @Override
    public Long getOfficerIdByPayerID(String payerLogin) {
        long res = 0L;
        try (PreparedStatement ps = connection.prepareStatement(
                DaoFactory.getQuery("get.officer.id.by.payer.login"))) {
            ps.setString(1, payerLogin);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res = rs.getLong("taxofficer");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return res;
    }

    @Override
    public Long getPayerIdByLogin(String payerLogin) {
        long res = 0L;
        try (PreparedStatement ps = connection.prepareStatement(
                DaoFactory.getQuery("get.payer.id.by.login"))) {
            ps.setString(1, payerLogin);
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
    public void addNewPayer(Payer payer) {
        try (PreparedStatement ps = connection.prepareStatement(
                DaoFactory.getQuery("add.new.payer"))) {
            ps.setString(1, payer.getLogin());
            ps.setString(2, payer.getName());
            ps.setString(3, payer.getPassword());
            ps.setInt(4, payer.getRole());
            ps.setLong(5, payer.getOfficerID());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public boolean matchForLoginAndPassword(String login, String password) {
        boolean result = false;
        try (PreparedStatement ps = connection.prepareStatement(
                DaoFactory.getQuery("match.for.payer.login.and.password"))) {
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
    public boolean notMatchForLogin(String login) {
        boolean result = true;
        try (PreparedStatement ps = connection.prepareStatement(
                DaoFactory.getQuery("not.match.for.payer.login"))) {
            ps.setString(1, login);
            if (ps.executeQuery().next()) {
                result = false;
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
