package com.example.test.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop;

    public static Properties initProperties() {
        prop = new Properties();

        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config/config.properties")) {

            if (input == null) {
                throw new RuntimeException("❌ config.properties not found in resources/config/");
            }

            prop.load(input);
            System.out.println("✅ Loaded configuration successfully.");

        } catch (IOException e) {
            System.out.println("❌ Failed to load config.properties: " + e.getMessage());
            throw new RuntimeException("Could not load config file", e);
        }

        return prop;
    }

    public static String getProperty(String key) {
        if (prop == null) {
            initProperties();
        }
        return prop.getProperty(key);
    }
}
