package org.model.dao.implement;

import org.apache.commons.dbcp2.BasicDataSource;
import org.model.service.PropertyManager;

import javax.sql.DataSource;

public class ConnectionPoolHolder {
    private static volatile DataSource dataSource;

    public static DataSource getDataSource(){
        if (dataSource == null){
            PropertyManager pm = new PropertyManager("/database/init.properties");
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl(pm.getDataBaseProperties().getProperty("url"));
                    ds.setUsername(pm.getDataBaseProperties()
                                   .getProperty("username"));
                    ds.setPassword(pm.getDataBaseProperties()
                                   .getProperty("password"));
                    ds.setMinIdle(Integer.valueOf(pm.getDataBaseProperties()
                                                  .getProperty("MinIdle")));
                    ds.setMaxTotal(Integer.valueOf(pm.getDataBaseProperties()
                                                   .getProperty("MaxTotal")));
                    ds.setMaxIdle(Integer.valueOf(pm.getDataBaseProperties()
                                                  .getProperty("MaxIdle")));
                    ds.setMaxOpenPreparedStatements(Integer.valueOf(
                            pm.getDataBaseProperties()
                              .getProperty("MaxOpenPreparedStatements")));
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }

}
