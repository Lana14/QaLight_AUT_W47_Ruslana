package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import utils.Constants;
import utils.EmailAddressCreator;

import java.time.Instant;

import static com.codeborne.selenide.Selenide.open;

public class UserRegistration extends BasePage {

    @BeforeMethod
    public void openUrl() {
        open(Constants.URL);
    }

    @Test(description = "Register as a new user")
    void signUp(){
        String loginName = "TestUser_" + Instant.now().getEpochSecond();
        String email = EmailAddressCreator.createRandomAddress();
        String password = "TestPassw0rd@" + Instant.now().getEpochSecond() + "!$";

        homePage.openLoginPage();
        loginPage.openRegisterPage();
        registerPage.insertUserLoginName(loginName)
                .insertEmail(email)
                .insertPassword(password)
                .confirmPassword(password)
                .clickSubmitButton();
        homePage.verifyHomePageIsOpenedByRegisteredUser(loginName);
    }

    @Test(description = "Verify that User can't use a duplicated email while registration")
    void duplicateEmailAddressError(){
        String loginName = "TestUser_" + Instant.now().getEpochSecond();
        String password = "TestPassw0rd@" + Instant.now().getEpochSecond() + "!$";

        homePage.openLoginPage();
        loginPage.openRegisterPage();
        registerPage.insertUserLoginName(loginName)
                .insertEmail(Constants.USER_EMAIL)
                .insertPassword(password)
                .confirmPassword(password)
                .clickSubmitButton();
        registerPage.duplicateEmailAddressErrorIsDisplayed();

    }
}
