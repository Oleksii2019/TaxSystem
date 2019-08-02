package org.model.service;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
    private static final Logger LOGGER =
            Logger.getLogger(PropertyManager.class);
    private Properties dataBaseProperties;

    public PropertyManager(String path) {
        dataBaseProperties = new Properties();
        try {
            InputStream in = getClass().getResourceAsStream(path);
            dataBaseProperties.load(in);
            in.close();
        } catch(IOException e) {
            LOGGER.error(e);
        }
    }

    public Properties getDataBaseProperties() {
        return dataBaseProperties;
    }

}
