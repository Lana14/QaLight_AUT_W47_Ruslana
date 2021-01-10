package tests;

import org.testng.annotations.Test;
import pages.BasePage;
import utils.*;

import static steps.CommonSteps.*;

public class UserRegistrationTests extends BasePage {

    @Test(description = "A new user can register successfully",
            retryAnalyzer = RetryAnalyzer.class)
    void signUp() {
        String loginName = generateRandomUserName();
        String email = createRandomAddress();
        String password = generateRandomPassword();

        homePage.openLoginPage();
        loginPage.openRegisterPage();
        registerPage.insertUserLoginName(loginName)
                .insertEmail(email)
                .insertPassword(password)
                .confirmPassword(password)
                .clickSubmitButton();
        homePage.verifyHomePageIsOpenedByRegisteredUser(loginName);
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
        String password = generateRandomPassword();

        homePage.openLoginPage()
                .openRegisterPage();
        registerPage.insertUserLoginName("Test$")
                .insertEmail(email)
                .insertPassword(password)
                .clickSubmitButton();
        registerPage.verifyErrorIncorrectUserNameIsEntered();
    }

    @Test(description = "Validation error appears when an incorrect email address is entered",
            retryAnalyzer = RetryAnalyzer.class)
    void validationErrorAppearsWhenIncorrectEmailAddressIsEntered() {
        String loginName = generateRandomUserName();
        String email = createRandomAddress();
        String password = generateRandomPassword();

        homePage.openLoginPage()
                .openRegisterPage();
        registerPage.insertUserLoginName(loginName)
                .insertEmail(email.replace(".", ""))
                .insertPassword(password)
                .confirmPassword(password)
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
        String loginName = generateRandomUserName();
        String email = createRandomAddress();
        String password = generateRandomPassword();

        homePage.openLoginPage()
                .openRegisterPage();
        registerPage.insertUserLoginName(loginName)
                .insertEmail(email)
                .insertPassword(password)
                .clickSubmitButton();
        registerPage.verifyErrorPasswordConfirmationFieldIsEmpty();
    }

    @Test(description = "Validation error appears when passwords do not match",
            retryAnalyzer = RetryAnalyzer.class)
    void validationErrorAppearsWhenPasswordsDoNotMatch() {
        String loginName = generateRandomUserName();
        String email = createRandomAddress();
        String password = generateRandomPassword();

        homePage.openLoginPage()
                .openRegisterPage();
        registerPage.insertUserLoginName(loginName)
                .insertEmail(email)
                .insertPassword(password)
                .confirmPassword(Constants.USER_PASSWORD)
                .clickSubmitButton();
        registerPage.verifyErrorPasswordsDoNotMatch();
    }

    @Test(description = "Verify that User can't use a duplicated email while registration",
            retryAnalyzer = RetryAnalyzer.class)
    void duplicateEmailAddressError() {
        String loginName = generateRandomUserName();
        String password = generateRandomPassword();

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
