package utils;

import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static Properties properties;

    private static void readProperties() {
        properties = new Properties();
        try {
            properties.load(PropertyReader.class.getClassLoader().
                    getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        if (properties == null) {
            readProperties();
        }
        return properties.getProperty(key);
    }

    public static String getUser() {
        return getProperty("user");
    }

    public static String getPassword() {
        return getProperty("password");
    }

    public static String getApiUrl() {
        return getProperty("api.url");
    }

    public static String getBooksUri() {
        return getProperty("books.uri");
    }

}
