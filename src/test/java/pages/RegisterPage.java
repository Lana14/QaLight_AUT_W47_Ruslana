package pages;

import common.logger.CustomLogger;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class RegisterPage {
    RegisterPageLocators registerPageLocators = new RegisterPageLocators();

    @Step("Insert user login")
    public RegisterPage insertUserLoginName(String loginName) {
        $(byXpath(registerPageLocators.headerTitle)).shouldBe(visible);
        $(byXpath(registerPageLocators.userLoginName)).sendKeys(loginName);
        CustomLogger.logger.info("ok");
        return this;
    }

    @Step("Insert email")
    public RegisterPage insertEmail(String email) {
        $(byXpath(registerPageLocators.userEmail)).sendKeys(email);
        CustomLogger.logger.info("ok");
        return this;
    }

    @Step("Insert password")
    public RegisterPage insertPassword(String password) {
        $(byXpath(registerPageLocators.userPassword)).sendKeys(password);
        CustomLogger.logger.info("ok");
        return this;
    }

    @Step("Confirm password")
    public RegisterPage confirmPassword(String password) {
        $(byXpath(registerPageLocators.userPasswordConfirmation)).sendKeys(password);
        CustomLogger.logger.info("ok");
        return this;
    }

    @Step("Submit the registration form")
    public HomePage clickSubmitButton() {
        $(byXpath(registerPageLocators.submitButton)).shouldBe(visible).click();
        CustomLogger.logger.info("ok");
        return new HomePage();
    }

    @Step("Verify the validation error is displayed when the UserLoginName field is empty")
    public RegisterPage verifyErrorUserLoginNameFieldIsEmpty() {
        $(byXpath(registerPageLocators.error), 0).shouldBe(visible)
                .shouldHave(exactText("ОШИБКА: Пожалуйста, введите имя пользователя."));
        CustomLogger.logger.info("ok");
        return this;
    }

    @Step("Verify the validation error is displayed when the Email field is empty")
    public RegisterPage verifyErrorEmailFieldIsEmpty() {
        $(byXpath(registerPageLocators.error), 1).shouldBe(visible)
                .shouldHave(exactText("ОШИБКА: Пожалуйста, введите ваш адрес email."));
        CustomLogger.logger.info("ok");
        return this;
    }

    @Step("Verify the validation error is displayed when the Password field is empty")
    public RegisterPage verifyErrorPasswordFieldIsEmpty() {
        $(byXpath(registerPageLocators.error), 2).shouldBe(visible)
                .shouldHave(exactText("Error: Please enter a password."));
        CustomLogger.logger.info("ok");
        return this;
    }

    @Step("Verify the validation error is displayed when the Password Confirmation field is empty")
    public RegisterPage verifyErrorPasswordConfirmationFieldIsEmpty() {
        $(byXpath(registerPageLocators.error)).shouldBe(visible)
                .shouldHave(exactText("Error: Please enter a password."));
        CustomLogger.logger.info("ok");
        return this;
    }

    @Step("Verify the validation error is displayed when an incorrect user name is entered")
    public RegisterPage verifyErrorIncorrectUserNameIsEntered() {
        $(byXpath(registerPageLocators.error)).shouldBe(visible)
                .shouldHave(exactText("ОШИБКА: Это имя пользователя некорректно, " +
                        "поскольку оно содержит недопустимые символы." +
                        " Пожалуйста, введите корректное имя пользователя."));
        CustomLogger.logger.info("ok");
        return this;
    }

    @Step
    public RegisterPage clearEmailField() {
        $(byXpath(registerPageLocators.userEmail)).clear();
        CustomLogger.logger.info("ok");
        return this;
    }

    @Step("Verify the validation error is displayed when an incorrect email address is entered")
    public RegisterPage verifyErrorIncorrectEmailAddressIsEntered() {
        $(byXpath(registerPageLocators.error)).shouldBe(visible)
                .shouldHave(exactText("ОШИБКА: Некорректный адрес email."));
        CustomLogger.logger.info("ok");
        return this;
    }

    @Step("Verify the validation error is displayed when passwords do not match")
    public RegisterPage verifyErrorPasswordsDoNotMatch() {
        $(byXpath(registerPageLocators.error)).shouldBe(visible)
                .shouldHave(exactText("Error: Passwords don’t match. " +
                        "Please enter the same password in both password fields."));
        CustomLogger.logger.info("ok");
        return this;
    }

    @Step("Verify that the duplicate email address error is displayed")
    public RegisterPage duplicateEmailAddressErrorIsDisplayed() {
        $(byXpath(registerPageLocators.error)).shouldBe(visible)
                .shouldHave(exactText("ОШИБКА: Этот email уже зарегистрирован. Пожалуйста, введите другой."));
        CustomLogger.logger.info("ok");
        return this;
    }
}
