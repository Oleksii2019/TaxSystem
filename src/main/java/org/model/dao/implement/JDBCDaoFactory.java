package org.model.dao.implement;

import org.model.dao.DaoFactory;
import org.model.dao.OfficerDao;
import org.model.dao.PayerDao;
import org.model.dao.ReportDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public PayerDao createPayerDao() {
        return new JDBCPayerDao(getConnection());
    }

    @Override
    public OfficerDao createOfficerDao() {
        return new JDBCOfficerDao(getConnection());
    }

    @Override
    public ReportDao createReportDao() {
        return new JDBCReportDao(getConnection());
    }

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

}
