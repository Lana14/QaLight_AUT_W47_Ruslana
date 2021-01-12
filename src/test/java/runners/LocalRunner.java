package runners;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.Constants;
import utils.Props;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;
import static utils.Props.*;

public class LocalRunner {

    @BeforeClass()
    public void setUp() {
        Configuration.timeout = 30000;
        Configuration.startMaximized = true;
        Configuration.browser = "firefox";
    }

    @BeforeMethod
    public void openUrl() throws IOException{
        initProperties();
        open(Props.website_url);
    }

    @AfterMethod()
    public void closeWebDriver() {
        resetProperties();
        Selenide.closeWebDriver();
    }
}
