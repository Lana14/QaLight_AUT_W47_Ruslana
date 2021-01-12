package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class RegisterPage {
    String headerTitle = "//h1[text()='Регистрация']";
    String userLoginName = "//input[@id='user_login']";
    String userEmail = "//input[@id='user_email']";
    String userPassword = "//input[@id='pass1']";
    String userPasswordConfirmation = "//input[@id='pass2']";
    String submitButton = "//button[@name='submit']";
    String error = "//li[@class='tml-error']";

    @Step("Insert user login")
    public RegisterPage insertUserLoginName(String loginName) {
        $(byXpath(headerTitle)).shouldBe(visible);
        $(byXpath(userLoginName)).sendKeys(loginName);
        return this;
    }

    @Step("Insert email")
    public RegisterPage insertEmail(String email) {
        $(byXpath(userEmail)).sendKeys(email);
        return this;
    }

    @Step("Insert password")
    public RegisterPage insertPassword(String password) {
        $(byXpath(userPassword)).sendKeys(password);
        return this;
    }

    @Step("Confirm password")
    public RegisterPage confirmPassword(String password) {
        $(byXpath(userPasswordConfirmation)).sendKeys(password);
        return this;
    }

    @Step("Submit the registration form")
    public HomePage clickSubmitButton() {
        $(byXpath(submitButton)).shouldBe(visible).click();
        return new HomePage();
    }

    @Step("Verify the validation error is displayed when the UserLoginName field is empty")
    public RegisterPage verifyErrorUserLoginNameFieldIsEmpty() {
        $(byXpath(error), 0).shouldBe(visible)
                .shouldHave(exactText("ОШИБКА: Пожалуйста, введите имя пользователя."));
        return this;
    }

    @Step("Verify the validation error is displayed when the Email field is empty")
    public RegisterPage verifyErrorEmailFieldIsEmpty() {
        $(byXpath(error), 1).shouldBe(visible)
                .shouldHave(exactText("ОШИБКА: Пожалуйста, введите ваш адрес email."));
        return this;
    }

    @Step("Verify the validation error is displayed when the Password field is empty")
    public RegisterPage verifyErrorPasswordFieldIsEmpty() {
        $(byXpath(error), 2).shouldBe(visible)
                .shouldHave(exactText("Error: Please enter a password."));
        return this;
    }

    @Step("Verify the validation error is displayed when the Password Confirmation field is empty")
    public RegisterPage verifyErrorPasswordConfirmationFieldIsEmpty() {
        $(byXpath(error)).shouldBe(visible)
                .shouldHave(exactText("Error: Please enter a password."));
        return this;
    }

    @Step("Verify the validation error is displayed when an incorrect user name is entered")
    public RegisterPage verifyErrorIncorrectUserNameIsEntered() {
        $(byXpath(error)).shouldBe(visible)
                .shouldHave(exactText("ОШИБКА: Это имя пользователя некорректно, поскольку оно содержит недопустимые символы." +
                        " Пожалуйста, введите корректное имя пользователя."));
        return this;
    }

    @Step
    public RegisterPage clearEmailField() {
        $(byXpath(userEmail)).clear();
        return this;
    }

    @Step("Verify the validation error is displayed when an incorrect email address is entered")
    public RegisterPage verifyErrorIncorrectEmailAddressIsEntered() {
        $(byXpath(error)).shouldBe(visible)
                .shouldHave(exactText("ОШИБКА: Некорректный адрес email."));
        return this;
    }

    @Step("Verify the validation error is displayed when passwords do not match")
    public RegisterPage verifyErrorPasswordsDoNotMatch() {
        $(byXpath(error)).shouldBe(visible)
                .shouldHave(exactText("Error: Passwords don’t match. " +
                        "Please enter the same password in both password fields."));
        return this;
    }

    @Step("Verify that the duplicate email address error is displayed")
    public RegisterPage duplicateEmailAddressErrorIsDisplayed() {
        $(byXpath(error)).shouldBe(visible)
                .shouldHave(exactText("ОШИБКА: Этот email уже зарегистрирован. Пожалуйста, введите другой."));
        return this;
    }
}
