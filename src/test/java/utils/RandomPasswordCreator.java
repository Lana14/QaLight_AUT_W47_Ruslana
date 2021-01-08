package utils;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.Instant;

public class RandomPasswordCreator {
    @Step
    public static String generateRandomPassword() {
        String password = "TestPassword@" + Instant.now().getEpochSecond()
                + RandomStringUtils.randomAlphabetic(10) + "!$";
        return password;
    }
}
