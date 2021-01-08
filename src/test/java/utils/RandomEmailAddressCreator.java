package utils;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.Instant;

import static utils.Constants.*;

public class RandomEmailAddressCreator {
    @Step
    public static String createRandomAddress() {
        long unixTime = Instant.now().getEpochSecond();
        return USER_EMAIL_PREFIX + unixTime + "+" + RandomStringUtils.randomAlphabetic(10) + USER_EMAIL_SUFFIX;
    }
}
