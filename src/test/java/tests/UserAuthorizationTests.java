package tests;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import utils.Constants;
import utils.EmailAddressCreator;

import java.time.Instant;

import static com.codeborne.selenide.Selenide.open;

public class UserAuthorizationTests extends BasePage {

    @BeforeMethod
    public void openUrl() {
        open(Constants.URL);
    }

    @Test(description = "User can login successfully using registered user name")
    void logInWithRegisteredUserName() {
        homePage.openLoginPage();
        loginPage.insertUserLoginName(Constants.USER_LOGIN_NAME)
                .insertPassword(Constants.USER_PASSWORD)
                .clickSubmitButton();
        dashboardPage.verifyDashboardPageIsOpenedAfterUserLogin(Constants.USER_LOGIN_NAME);
    }

    @Test(description = "User can login successfully using registered user email address")
    void logInRegisteredUserEmailAddress() {
        homePage.openLoginPage();
        loginPage.insertUserLoginName(Constants.USER_EMAIL)
                .insertPassword(Constants.USER_PASSWORD)
                .clickSubmitButton();
        dashboardPage.verifyDashboardPageIsOpenedAfterUserLogin(Constants.USER_LOGIN_NAME);
    }

    @Test(description = "User can logout successfully")
    void logOut() {
        homePage.openLoginPage();
        loginPage.loginAs(Constants.USER_LOGIN_NAME, Constants.USER_PASSWORD)
                .loginOut()
                .verifyLogoutMessageIsDisplayed();
    }

    @Test(description = "Validation errors appear while submitting an empty login form")
    void validationErrorsAppearWhileSubmittingEmptyLoginForm() {
        homePage.openLoginPage();
        loginPage.clickSubmitButton();
        loginPage.verifyUserLoginNameFieldEmptyError()
                .verifyPasswordFieldEmptyError();
    }

    @Test(description = "Validation error appears when unknown user name is entered")
    void validationErrorAppearsWhenUnknownUserNameIsEntered() {
        String loginName = "TestUser_" + Instant.now().getEpochSecond();

        homePage.openLoginPage();
        loginPage.insertUserLoginName(loginName)
                .insertPassword(Constants.USER_PASSWORD)
                .clickSubmitButton();
        loginPage.verifyErrorUnknownUserNameIsEntered();
    }

    @Test(description = "Validation error appears when unknown user email address is entered")
    void validationErrorAppearsWhenUnknownUserEmailAddressIsEntered() {
        String email = EmailAddressCreator.createRandomAddress();

        homePage.openLoginPage();
        loginPage.insertUserLoginName(email)
                .insertPassword(Constants.USER_PASSWORD)
                .clickSubmitButton();
        loginPage.verifyErrorUnknownUserEmailAddressIsEntered();
    }

    @Test(description = "Validation error appears when incorrect password is entered - Login with login name")
    void validationErrorAppearsWhenIncorrectPasswordIsEnteredLoginWithUserName() {
        String password = "TestPassw0rd@" + Instant.now().getEpochSecond() + "!$";

        homePage.openLoginPage();
        loginPage.insertUserLoginName(Constants.USER_LOGIN_NAME)
                .insertPassword(password)
                .clickSubmitButton();
        loginPage.verifyErrorIncorrectPasswordIsEnteredLoginWithLoginName(Constants.USER_LOGIN_NAME);
    }

    @Test(description = "Validation error appears when incorrect password is entered - Login with email")
    void validationErrorAppearsWhenIncorrectPasswordIsEnteredLoginWithEmail() {
        String password = "TestPassw0rd@" + Instant.now().getEpochSecond() + "!$";

        homePage.openLoginPage();
        loginPage.insertUserLoginName(Constants.USER_EMAIL)
                .insertPassword(password)
                .clickSubmitButton();
        loginPage.verifyErrorIncorrectPasswordIsEnteredLoginWithUserEmail(Constants.USER_EMAIL);
    }

    @AfterMethod
    public void closeWindow() {
        Selenide.closeWindow();
    }
}
