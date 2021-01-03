package utils;

import io.qameta.allure.Step;

import java.time.Instant;

import static utils.Constants.*;

public class EmailAddressCreator {
    @Step
    public static String createRandomAddress() {
        long unixTime = Instant.now().getEpochSecond();
        return USER_EMAIL_PREFIX + unixTime + USER_EMAIL_SUFFIX;
    }
}
