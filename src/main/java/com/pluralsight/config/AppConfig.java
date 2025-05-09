package com.pluralsight.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private static final Properties properties = new Properties();
    private static final String CSV_FILE_PATH_PROPERTY = "transaction.file.path";

    static {
        try (InputStream in =
                     AppConfig.class.getClassLoader()
                             .getResourceAsStream("application.properties")) {
            if (in == null) {
                throw new IllegalStateException("application.properties not found");
            }
            properties.load(in);
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static String getCsvFilePath() {
        return properties.getProperty(CSV_FILE_PATH_PROPERTY);
    }

}
