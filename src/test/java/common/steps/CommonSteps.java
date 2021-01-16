package common.steps;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import java.time.Instant;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;
import static common.utils.Constants.USER_EMAIL_PREFIX;
import static common.utils.Constants.USER_EMAIL_SUFFIX;

public class CommonSteps {

    @Step
    public static void checkUrl(String mustContains, int timeout) {
        boolean conditionForUrl = false;
        for (int i = 0; i < timeout * 2; i++) {
            if (url().contains(mustContains)) {
                conditionForUrl = true;
                break;
            } else {
                sleep(500);
            }
        }
        if (!conditionForUrl) {
            getAndAttachScreenshot();
            Assert.fail("Actual URL: " + url() + "\n" + "Expected URL: " + mustContains);
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] getAndAttachScreenshot() {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Step
    public static String createRandomAddress() {
        long unixTime = Instant.now().getEpochSecond();
        return USER_EMAIL_PREFIX + unixTime + "+" + RandomStringUtils.randomAlphabetic(10) + USER_EMAIL_SUFFIX;
    }
}
