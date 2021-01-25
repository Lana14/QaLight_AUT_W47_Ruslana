package common.utils.config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import static com.codeborne.selenide.Selenide.open;
import static common.logger.CustomLogger.logger;
import static common.utils.Global.*;

public class SelenoidConfig {
    public void createWebDriverInstance() {
        ChromeOptions options = new ChromeOptions();
        if (globalDevice != null && globalDevice.contains("Nexus 5")) {
            Map<String, String> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceName", "Nexus 5");
            options.setExperimentalOption("mobileEmulation", mobileEmulation);
        }
        options.addArguments("--disable-notifications");
        Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        Configuration.browserCapabilities.setCapability("goog:loggingPrefs", logPrefs);
        Configuration.browserCapabilities.setCapability("sessionTimeout", "5m");
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.timeout = 60000;
        Configuration.reportsFolder = "target/screenshots";
        Configuration.baseUrl = "";
        Configuration.browser = "chrome";
        globalBrowserName = "Chrome";
        Configuration.browserVersion = "87";
        Configuration.clickViaJs = true;
        Configuration.browserCapabilities.setCapability("enableVNC", false);
        Configuration.browserCapabilities.setCapability("enableVideo", false);
        if (globalDevice == null || !globalDevice.contains("Nexus 5")) {
            Configuration.startMaximized = true;
            globalDevice = "Desktop";
        }
        open();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        logger.info("ok");
    }
}
