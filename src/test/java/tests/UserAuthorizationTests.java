package tests;

import org.testng.annotations.Test;
import pages.BasePage;
import utils.*;

import static steps.CommonSteps.*;

public class UserAuthorizationTests extends BasePage {

    @Test(description = "User can login successfully using registered user name")
    void logInWithRegisteredUserName() {
        homePage.openLoginPage();
        loginPage.insertUserLoginName(Constants.USER_LOGIN_NAME)
                .insertPassword(Constants.USER_PASSWORD)
                .clickSubmitButton();
        dashboardPage.verifyDashboardPageIsOpenedAfterUserLogin(Constants.USER_LOGIN_NAME);
    }

    @Test(description = "User can login successfully using registered user email address")
    void logInWithRegisteredUserEmailAddress() {
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
                .logOut()
                .verifyLogoutMessageIsDisplayed();
    }

    @Test(description = "Validation errors appear while submitting an empty login form")
    void validationErrorsAppearWhileSubmittingEmptyLoginForm() {
        homePage.openLoginPage();
        loginPage.clickSubmitButton();
        loginPage.verifyErrorUserLoginNameFieldIsEmpty()
                .verifyErrorPasswordFieldIsEmpty();
    }

    @Test(description = "Validation error appears when unknown user name is entered")
    void validationErrorAppearsWhenUnknownUserNameIsEntered() {
        homePage.openLoginPage();
        loginPage.insertUserLoginName(Props.login)
                .insertPassword(Constants.USER_PASSWORD)
                .clickSubmitButton();
        loginPage.verifyErrorUnknownUserNameIsEntered();
    }

    @Test(description = "Validation error appears when unknown user email address is entered")
    void validationErrorAppearsWhenUnknownUserEmailAddressIsEntered() {
        String email = createRandomAddress();

        homePage.openLoginPage();
        loginPage.insertUserLoginName(email)
                .insertPassword(Constants.USER_PASSWORD)
                .clickSubmitButton();
        loginPage.verifyErrorUnknownUserEmailAddressIsEntered();
    }

    @Test(description = "Validation error appears when incorrect password is entered - Login with login name")
    void validationErrorAppearsWhenIncorrectPasswordIsEnteredLoginWithUserName() {
        homePage.openLoginPage();
        loginPage.insertUserLoginName(Constants.USER_LOGIN_NAME)
                .insertPassword(Props.password)
                .clickSubmitButton();
        loginPage.verifyErrorIncorrectPasswordIsEnteredLoginWithLoginName(Constants.USER_LOGIN_NAME);
    }

    @Test(description = "Validation error appears when incorrect password is entered - Login with email")
    void validationErrorAppearsWhenIncorrectPasswordIsEnteredLoginWithEmail() {
        homePage.openLoginPage();
        loginPage.insertUserLoginName(Constants.USER_EMAIL)
                .insertPassword(Props.password)
                .clickSubmitButton();
        loginPage.verifyErrorIncorrectPasswordIsEnteredLoginWithUserEmail(Constants.USER_EMAIL);
    }
}
