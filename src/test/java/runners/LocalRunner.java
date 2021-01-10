package runners;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.Constants;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

public class LocalRunner {

    @BeforeClass()
    public void setUp() throws IOException {
        Configuration.timeout = 30000;
        Configuration.startMaximized = true;
        Configuration.browser = "firefox";
        //initProperties();
    }

    @BeforeMethod
    public void openUrl() {
        open(Constants.URL);
    }

    @AfterMethod()
    public void closeWebDriver() {
        //resetProperties();
        Selenide.closeWebDriver();
    }
}
