package utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.Properties;

public class Props {

    public static final String PATH_TO_PROPERTIES = "src/test/resources/config.properties";
    public static String website_url = "";
    public static String login = "";
    public static String password = "";

    public static void initProperties() throws IOException {
        //инициализируем специальный объект Properties
        Properties prop = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(PATH_TO_PROPERTIES)) {
            //обращаемся к файлу и получаем данные
            prop.load(fileInputStream);
            website_url = prop.getProperty("website_url");
            login = prop.getProperty("login") + Instant.now().getEpochSecond() + " "
                    + RandomStringUtils.randomAlphabetic(10);
            password = prop.getProperty("password") + Instant.now().getEpochSecond()
                    + RandomStringUtils.randomAlphabetic(10) + "!$";
            //печатаем полученные данные в консоль
            System.out.println(
                    "site: " + website_url
                            + "\nlogin: " + login
                            + "\npassword: " + password
            );
        }
    }

    public static void resetProperties() {
        website_url = "";
        login = "";
        password = "";
    }
}
