package utils;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.Instant;

public class RandomUserNameCreator {
    @Step
    public static String generateRandomUserName() {
        String userName = "TestUser_" + Instant.now().getEpochSecond() + " "
                + RandomStringUtils.randomAlphabetic(10);
        return userName;
    }
}
