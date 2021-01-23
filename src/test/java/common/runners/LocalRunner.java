package common.runners;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.*;
import common.utils.config.Props;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;
import static common.utils.config.Props.*;

public class LocalRunner {

    @BeforeClass()
    @Parameters("browser")
    public void setUp(@Optional String browser) {
        Configuration.timeout = 30000;
        Configuration.startMaximized = true;
        try {
            switch (browser) {
                case "chrome":
                    Configuration.browser = "chrome";
                    break;
                case "firefox":
                    Configuration.browser = "firefox";
                    break;
            }
        } catch (NullPointerException e) {
            System.out.println("Browser is not defined");
        }
    }

    @BeforeMethod
    public void openUrl() throws IOException {
        initProperties();
        open(Props.website_url);
    }

    @AfterMethod()
    public void closeWebDriver() {
        resetProperties();
        Selenide.closeWebDriver();
    }
}
