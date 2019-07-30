package org.model.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
    private Properties dataBaseProperties;

    public PropertyManager(String path) {
        dataBaseProperties = new Properties();
        try {
            InputStream in = getClass().getResourceAsStream(path);
            dataBaseProperties.load(in);
            in.close();
        } catch(IOException e) {
            System.out.println(e);
        }
    }

    public Properties getDataBaseProperties() {
        return dataBaseProperties;
    }

}
