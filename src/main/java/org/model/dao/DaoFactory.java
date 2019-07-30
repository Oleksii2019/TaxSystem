package org.model.dao;

import org.model.dao.implement.JDBCDaoFactory;
import org.model.service.PropertyManager;

import java.util.Properties;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;
    private static Properties queries;

    public abstract PayerDao createPayerDao();
    public abstract OfficerDao createOfficerDao();
    public abstract ReportDao createReportDao();

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            PropertyManager pm = new PropertyManager(
                    "/database/queries.properties");
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    DaoFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                    queries = pm.getDataBaseProperties();
                }
            }
        }
        return daoFactory;
    }

    public static String getQuery(String name) {
        return queries.getProperty(name);
    }
}
