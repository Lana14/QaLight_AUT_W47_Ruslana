package common.runners;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import common.utils.config.Props;
import org.testng.annotations.Parameters;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;
import static common.utils.config.Props.*;

public class LocalRunner {

    @BeforeClass()
    @Parameters("browser")
    public void setUp(String browser) {
        Configuration.timeout = 30000;
        Configuration.startMaximized = true;
        if (browser.equals("firefox")) {
            Configuration.browser = "firefox";
        } else if (browser.equals("chrome")) {
            Configuration.browser = "chrome";
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
