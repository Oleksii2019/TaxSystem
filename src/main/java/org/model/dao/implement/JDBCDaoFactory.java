package org.model.dao.implement;

import org.model.dao.DaoFactory;
import org.model.dao.PayerDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public PayerDao createPayerDao() {
        return new JDBCPayerDao(getConnection());
    }
//    @Override
//    public StudentDao createStudentDao() {
//        return new JDBCStudentDao(getConnection());
//    }

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println(e + " - нет подключения к БД");
            throw new RuntimeException(e);
        }
    }

}
