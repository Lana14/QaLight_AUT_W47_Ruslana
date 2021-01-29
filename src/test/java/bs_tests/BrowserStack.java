package bs_tests;

import com.codeborne.selenide.WebDriverRunner;
import common.utils.config.Props;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.net.URL;

import static com.codeborne.selenide.Selenide.open;
import static common.utils.config.Props.initProperties;
import static common.utils.config.Props.resetProperties;

public class BrowserStack {

    public RemoteWebDriver driver;

    public static final String USERNAME = "ruslana12";
    public static final String AUTOMATE_KEY = "e4dnUAkGjUbyumiQwxQg";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("browser", "IE");
        caps.setCapability("browser_version", "11");
        caps.setCapability("name", "IE11");

        driver = new RemoteWebDriver(new URL(URL), caps);
        WebDriverRunner.setWebDriver(driver);

        initProperties();
        open(Props.website_url);
    }

    @AfterMethod(alwaysRun = true)
    public void closeWebDriver() {
        resetProperties();
        driver.quit();
    }
}
