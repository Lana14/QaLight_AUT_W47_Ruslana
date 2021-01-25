package common.runners;

import com.codeborne.selenide.Selenide;
import common.utils.config.Props;
import common.utils.config.SelenoidConfig;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.testng.annotations.*;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.driver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static common.steps.CommonSteps.writeLog;
import static common.utils.Global.*;
import static common.utils.config.Props.initProperties;
import static common.utils.config.Props.resetProperties;

public class SelenoidRunner {

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        globalDevice = "Desktop";
        SelenoidConfig selenoidConfig = new SelenoidConfig();
        selenoidConfig.createWebDriverInstance();
    }

    @BeforeMethod
    public void openUrl() throws IOException {
        initProperties();
        open(Props.website_url);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (globalBrowserName.equals("Chrome") && driver().hasWebDriverStarted()) {
            Logs logs = getWebDriver().manage().logs();
            writeLog(logs.get(LogType.BROWSER), "BrowserConsoleLog");
        }
        Selenide.closeWebDriver();
        resetGlobalVariablesAfterMethod();
        resetProperties();
    }

}
