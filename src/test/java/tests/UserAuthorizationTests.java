package tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import pages.BasePage;
import utils.*;

import static steps.CommonSteps.*;

public class UserAuthorizationTests extends BasePage {

    @Test(description = "User can login successfully using registered user name")
    void logInWithRegisteredUserName() {
        homePage.openLoginPage();
        loginPage.insertUserLoginName(User.NAME.getValue())
                .insertPassword(User.PASSWORD.getValue())
                .clickSubmitButton();
        dashboardPage.verifyDashboardPageIsOpenedAfterUserLogin(User.NAME.getValue());
    }

    @Test(description = "User can login successfully using registered user email address")
    void logInWithRegisteredUserEmailAddress() {
        homePage.openLoginPage();
        loginPage.insertUserLoginName(User.EMAIL.getValue())
                .insertPassword(User.PASSWORD.getValue())
                .clickSubmitButton();
        dashboardPage.verifyDashboardPageIsOpenedAfterUserLogin(User.NAME.getValue());
    }

    @Test(description = "User can logout successfully")
    void logOut() {
        homePage.openLoginPage();
        loginPage.loginAs(User.NAME.getValue(), User.PASSWORD.getValue())
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
        loginPage.insertUserLoginName(User.NAME.getValue() + RandomStringUtils.randomAlphabetic(10))
                .insertPassword(User.PASSWORD.getValue())
                .clickSubmitButton();
        loginPage.verifyErrorUnknownUserNameIsEntered();
    }

    @Test(description = "Validation error appears when unknown user email address is entered")
    void validationErrorAppearsWhenUnknownUserEmailAddressIsEntered() {
        String email = createRandomAddress();

        homePage.openLoginPage();
        loginPage.insertUserLoginName(email)
                .insertPassword(User.PASSWORD.getValue())
                .clickSubmitButton();
        loginPage.verifyErrorUnknownUserEmailAddressIsEntered();
    }

    @Test(description = "Validation error appears when incorrect password is entered - Login with login name")
    void validationErrorAppearsWhenIncorrectPasswordIsEnteredWhileLogInWithUserName() {
        homePage.openLoginPage();
        loginPage.insertUserLoginName(User.NAME.getValue())
                .insertPassword(User.PASSWORD.getValue() + RandomStringUtils.randomAlphabetic(10))
                .clickSubmitButton();
        loginPage.verifyErrorIncorrectPasswordIsEnteredLoginWithLoginName(User.NAME.getValue());
    }

    @Test(description = "Validation error appears when incorrect password is entered - Login with email")
    void validationErrorAppearsWhenIncorrectPasswordIsEnteredWhileLogInWithEmail() {
        homePage.openLoginPage();
        loginPage.insertUserLoginName(User.EMAIL.getValue())
                .insertPassword(User.PASSWORD.getValue() + RandomStringUtils.randomAlphabetic(10))
                .clickSubmitButton();
        loginPage.verifyErrorIncorrectPasswordIsEnteredLoginWithUserEmail(User.EMAIL.getValue());
    }
}
