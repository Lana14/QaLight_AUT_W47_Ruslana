package tests;

import common.test_data.users.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import pages.BasePage;

import static common.steps.CommonSteps.createRandomAddress;

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
    void verifyValidationErrorsAppearWhileSubmittingEmptyLoginForm() {
        homePage.openLoginPage();
        loginPage.clickSubmitButton();
        loginPage.verifyErrorUserLoginNameFieldIsEmpty()
                .verifyErrorPasswordFieldIsEmpty();
    }

    @Test(description = "Validation error appears when unknown user name is entered")
    void verifyValidationErrorAppearsWhenUnknownUserNameIsEntered() {
        homePage.openLoginPage();
        loginPage.insertUserLoginName(User.NAME.getValue() + RandomStringUtils.randomAlphabetic(10))
                .insertPassword(User.PASSWORD.getValue())
                .clickSubmitButton();
        loginPage.verifyErrorUnknownUserNameIsEntered();
    }

    @Test(description = "Validation error appears when unknown user email address is entered")
    void verifyValidationErrorAppearsWhenUnknownUserEmailAddressIsEntered() {
        String email = createRandomAddress();

        homePage.openLoginPage();
        loginPage.insertUserLoginName(email)
                .insertPassword(User.PASSWORD.getValue())
                .clickSubmitButton();
        loginPage.verifyErrorUnknownUserEmailAddressIsEntered();
    }

    @Test(description = "Validation error appears when incorrect password is entered - Login with login name")
    void verifyValidationErrorAppearsWhenIncorrectPasswordIsEnteredWhileLogInWithUserName() {
        homePage.openLoginPage();
        loginPage.insertUserLoginName(User.NAME.getValue())
                .insertPassword(User.PASSWORD.getValue() + RandomStringUtils.randomAlphabetic(10))
                .clickSubmitButton();
        loginPage.verifyErrorIncorrectPasswordIsEnteredLoginWithLoginName(User.NAME.getValue());
    }

    @Test(description = "Validation error appears when incorrect password is entered - Login with email")
    void verifyValidationErrorAppearsWhenIncorrectPasswordIsEnteredWhileLogInWithEmail() {
        homePage.openLoginPage();
        loginPage.insertUserLoginName(User.EMAIL.getValue())
                .insertPassword(User.PASSWORD.getValue() + RandomStringUtils.randomAlphabetic(10))
                .clickSubmitButton();
        loginPage.verifyErrorIncorrectPasswordIsEnteredLoginWithUserEmail(User.EMAIL.getValue());
    }
}
