package common.steps;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.Assert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;
import static common.utils.Global.USER_EMAIL_PREFIX;
import static common.utils.Global.USER_EMAIL_SUFFIX;

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

    @Step
    public static void writeLog(LogEntries entries, String logName) {
        try {
            File browserConsoleLog = new File("target//" + logName + ".txt");
            if (browserConsoleLog.exists()) {
                //noinspection ResultOfMethodCallIgnored
                browserConsoleLog.delete();
            }
            FileWriter writer = new FileWriter("target//" + logName + ".txt", true);
            for (LogEntry entry : entries) {
                Date date = new Date(entry.getTimestamp());
                SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss");
                if (logName.contains("Performance")) {
                    String message = entry.getMessage();
                    if (message.contains("\"status\":\"5") || message.contains("\"status\":\"4")) {
                        writer.write(ft.format(date) + " " + entry.getLevel() + " " + entry.getMessage() +
                                "\r\n\n");
                    }
                } else {
                    writer.write(ft.format(date) + " " + entry.getLevel() + " " + entry.getMessage() + "\r\n\n");
                }
            }
            writer.close();
            if (logName.contains("Performance")) {
                attachToAllureReportWithTypeJSON(logName + ".txt");
            } else {
                attachToAllureReport(logName + ".txt");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    @Attachment(value = "log", type = "application/json")
    public static byte[] attachToAllureReportWithTypeJSON(String resourceName) throws IOException {
        return Files.readAllBytes(Paths.get("target/", resourceName));
    }

    @SuppressWarnings("UnusedReturnValue")
    @Attachment
    public static byte[] attachToAllureReport(String resourceName) throws IOException {
        return Files.readAllBytes(Paths.get("target/", resourceName));
    }
}
