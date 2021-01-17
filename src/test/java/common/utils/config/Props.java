package common.utils.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Props {

    public static final String PATH_TO_PROPERTIES = "src/test/resources/config.properties";
    public static String website_url = "";

    public static void initProperties() throws IOException {
        //инициализируем специальный объект Properties
        Properties prop = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(PATH_TO_PROPERTIES)) {
            //обращаемся к файлу и получаем данные
            prop.load(fileInputStream);
            website_url = prop.getProperty("website_url");
            //печатаем полученные данные в консоль
            System.out.println(
                    "site: " + website_url
            );
        }
    }

    public static void resetProperties() {
        website_url = "";
    }
}
