package tests;

import org.testng.annotations.Test;
import pages.BasePage;
import utils.*;

import static steps.CommonSteps.*;

public class UserRegistrationTests extends BasePage {

    @Test(description = "A new user can register successfully",
            retryAnalyzer = RetryAnalyzer.class)
    void signUp() {
        String email = createRandomAddress();

        homePage.openLoginPage();
        loginPage.openRegisterPage();
        registerPage.insertUserLoginName(Props.login)
                .insertEmail(email)
                .insertPassword(Props.password)
                .confirmPassword(Props.password)
                .clickSubmitButton();
        homePage.verifyHomePageIsOpenedByRegisteredUser(Props.login);
    }

    @Test(description = "Validation errors appear while submitting an empty registration form",
            retryAnalyzer = RetryAnalyzer.class)
    void validationErrorsAppearWhileSubmittingEmptyRegistrationForm() {
        homePage.openLoginPage()
                .openRegisterPage();
        registerPage.clickSubmitButton();
        registerPage.verifyErrorUserLoginNameFieldIsEmpty()
                .verifyErrorEmailFieldIsEmpty()
                .verifyErrorPasswordFieldIsEmpty();
    }

    @Test(description = "Validation error appears when an incorrect user name is entered",
            retryAnalyzer = RetryAnalyzer.class)
    void validationErrorAppearsWhenIncorrectUserNameIsEntered() {
        String email = createRandomAddress();

        homePage.openLoginPage()
                .openRegisterPage();
        registerPage.insertUserLoginName("Test$")
                .insertEmail(email)
                .insertPassword(Props.password)
                .clickSubmitButton();
        registerPage.verifyErrorIncorrectUserNameIsEntered();
    }

    @Test(description = "Validation error appears when an incorrect email address is entered",
            retryAnalyzer = RetryAnalyzer.class)
    void validationErrorAppearsWhenIncorrectEmailAddressIsEntered() {
        String email = createRandomAddress();

        homePage.openLoginPage()
                .openRegisterPage();
        registerPage.insertUserLoginName(Props.login)
                .insertEmail(email.replace(".", ""))
                .insertPassword(Props.password)
                .confirmPassword(Props.password)
                .clickSubmitButton();
        registerPage.verifyErrorIncorrectEmailAddressIsEntered()
                .clearEmailField()
                .insertEmail(email.replace("@", ""))
                .clickSubmitButton();
        registerPage.verifyErrorIncorrectEmailAddressIsEntered();
    }

    @Test(description = "Validation error appears when the password is not confirmed",
            retryAnalyzer = RetryAnalyzer.class)
    void validationErrorAppearsWhenPasswordIsNotConfirmed() {
        String email = createRandomAddress();

        homePage.openLoginPage()
                .openRegisterPage();
        registerPage.insertUserLoginName(Props.login)
                .insertEmail(email)
                .insertPassword(Props.password)
                .clickSubmitButton();
        registerPage.verifyErrorPasswordConfirmationFieldIsEmpty();
    }

    @Test(description = "Validation error appears when passwords do not match",
            retryAnalyzer = RetryAnalyzer.class)
    void validationErrorAppearsWhenPasswordsDoNotMatch() {
        String email = createRandomAddress();

        homePage.openLoginPage()
                .openRegisterPage();
        registerPage.insertUserLoginName(Props.login)
                .insertEmail(email)
                .insertPassword(Props.password)
                .confirmPassword(Constants.USER_PASSWORD)
                .clickSubmitButton();
        registerPage.verifyErrorPasswordsDoNotMatch();
    }

    @Test(description = "Verify that User can't use a duplicated email while registration",
            retryAnalyzer = RetryAnalyzer.class)
    void duplicateEmailAddressError() {
        homePage.openLoginPage();
        loginPage.openRegisterPage();
        registerPage.insertUserLoginName(Props.login)
                .insertEmail(Constants.USER_EMAIL)
                .insertPassword(Props.password)
                .confirmPassword(Props.password)
                .clickSubmitButton();
        registerPage.duplicateEmailAddressErrorIsDisplayed();
    }
}
