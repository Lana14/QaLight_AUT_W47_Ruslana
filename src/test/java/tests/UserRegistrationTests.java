package tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import pages.BasePage;
import common.utils.*;

import static common.steps.CommonSteps.*;
import static common.utils.CsvWriter.writeToCSVFile;
import static common.utils.ExcelWriter.writeToExcelFile;

public class UserRegistrationTests extends BasePage {

    @Test(description = "A new user can register successfully")
    void signUp() {
        String email = createRandomAddress();
        String name = User.NAME.getValue() + RandomStringUtils.randomAlphabetic(10);
        String password = User.PASSWORD.getValue() + RandomStringUtils.randomAlphabetic(10);

        homePage.openLoginPage();
        loginPage.openRegisterPage();
        registerPage.insertUserLoginName(name)
                .insertEmail(email)
                .insertPassword(password)
                .confirmPassword(password)
                .clickSubmitButton();
        homePage.verifyHomePageIsOpenedByRegisteredUser(name);
        writeToExcelFile(name, email, password);
        writeToCSVFile(name, email, password, true);
    }

    @Test(description = "Validation errors appear while submitting an empty registration form")
    void validationErrorsAppearWhileSubmittingEmptyRegistrationForm() {
        homePage.openLoginPage()
                .openRegisterPage();
        registerPage.clickSubmitButton();
        registerPage.verifyErrorUserLoginNameFieldIsEmpty()
                .verifyErrorEmailFieldIsEmpty()
                .verifyErrorPasswordFieldIsEmpty();
    }

    @Test(description = "Validation error appears when an incorrect user name is entered")
    void validationErrorAppearsWhenIncorrectUserNameIsEntered() {
        String email = createRandomAddress();

        homePage.openLoginPage()
                .openRegisterPage();
        registerPage.insertUserLoginName("Test$")
                .insertEmail(email)
                .insertPassword(User.PASSWORD.getValue() + RandomStringUtils.randomAlphabetic(10))
                .clickSubmitButton();
        registerPage.verifyErrorIncorrectUserNameIsEntered();
    }

    @Test(description = "Validation error appears when an incorrect email address is entered")
    void validationErrorAppearsWhenIncorrectEmailAddressIsEntered() {
        String email = createRandomAddress();

        homePage.openLoginPage()
                .openRegisterPage();
        registerPage.insertUserLoginName(User.NAME.getValue() + RandomStringUtils.randomAlphabetic(10))
                .insertEmail(email.replace(".", ""))
                .insertPassword(User.PASSWORD.getValue())
                .confirmPassword(User.PASSWORD.getValue())
                .clickSubmitButton();
        registerPage.verifyErrorIncorrectEmailAddressIsEntered()
                .clearEmailField()
                .insertEmail(email.replace("@", ""))
                .clickSubmitButton();
        registerPage.verifyErrorIncorrectEmailAddressIsEntered();
    }

    @Test(description = "Validation error appears when the password is not confirmed")
    void validationErrorAppearsWhenPasswordIsNotConfirmed() {
        String email = createRandomAddress();

        homePage.openLoginPage()
                .openRegisterPage();
        registerPage.insertUserLoginName(User.NAME.getValue() + RandomStringUtils.randomAlphabetic(10))
                .insertEmail(email)
                .insertPassword(User.PASSWORD.getValue())
                .clickSubmitButton();
        registerPage.verifyErrorPasswordConfirmationFieldIsEmpty();
    }

    @Test(description = "Validation error appears when passwords do not match")
    void validationErrorAppearsWhenPasswordsDoNotMatch() {
        String email = createRandomAddress();

        homePage.openLoginPage()
                .openRegisterPage();
        registerPage.insertUserLoginName(User.NAME.getValue() + RandomStringUtils.randomAlphabetic(10))
                .insertEmail(email)
                .insertPassword(User.PASSWORD.getValue())
                .confirmPassword(User.PASSWORD.getValue() + RandomStringUtils.randomAlphabetic(10))
                .clickSubmitButton();
        registerPage.verifyErrorPasswordsDoNotMatch();
    }

    @Test(description = "Verify that User can't use a duplicated email while registration")
    void duplicateEmailAddressError() {
        homePage.openLoginPage();
        loginPage.openRegisterPage();
        registerPage.insertUserLoginName(User.NAME.getValue() + RandomStringUtils.randomAlphabetic(10))
                .insertEmail(User.EMAIL.getValue())
                .insertPassword(User.PASSWORD.getValue())
                .confirmPassword(User.PASSWORD.getValue())
                .clickSubmitButton();
        registerPage.duplicateEmailAddressErrorIsDisplayed();
    }
}
