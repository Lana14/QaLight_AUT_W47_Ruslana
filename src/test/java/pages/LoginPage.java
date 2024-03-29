package pages;

import common.logger.CustomLogger;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static common.logger.CustomLogger.logger;

public class LoginPage {
    LoginPageLocators loginPageLocators = new LoginPageLocators();

    @Step("Open the Register page")
    public RegisterPage openRegisterPage() {
        $(byXpath(loginPageLocators.headerTitle)).shouldBe(visible);
        $(byXpath(loginPageLocators.registerLink)).shouldBe(visible).click();
        CustomLogger.logger.info("ok");
        return new RegisterPage();
    }

    @Step("Insert user login")
    public LoginPage insertUserLoginName(String loginName) {
        $(byXpath(loginPageLocators.headerTitle)).shouldBe(visible);
        $(byXpath(loginPageLocators.userLoginName)).sendKeys(loginName);
        CustomLogger.logger.info("ok");
        return this;
    }

    @Step("Insert password")
    public LoginPage insertPassword(String password) {
        $(byXpath(loginPageLocators.userPassword)).sendKeys(password);
        CustomLogger.logger.info("ok");
        return this;
    }

    @Step("Submit the authorization credentials")
    public DashboardPage clickSubmitButton() {
        $(byXpath(loginPageLocators.submitButton)).click();
        CustomLogger.logger.info("ok");
        return new DashboardPage();
    }

    @Step("Login as an authorized user")
    public DashboardPage loginAs(String userName, String password) {
        $(byXpath(loginPageLocators.headerTitle)).shouldBe(visible);
        insertUserLoginName(userName);
        insertPassword(password);
        clickSubmitButton();
        CustomLogger.logger.info("ok");
        return new DashboardPage();
    }

    @Step("Verify that the logout message is displayed")
    public LoginPage verifyLogoutMessageIsDisplayed() {
        $(byXpath(loginPageLocators.headerTitle)).shouldBe(visible);
        $(byXpath(loginPageLocators.message)).shouldBe(visible)
                .shouldHave(exactText("Вы вышли из системы."));
        CustomLogger.logger.info("ok");
        return this;
    }

    @Step("Verify the validation error is displayed when the UserLoginName field is empty")
    public LoginPage verifyErrorUserLoginNameFieldIsEmpty() {
        $(byXpath(loginPageLocators.error), 0).shouldBe(visible)
                .shouldHave(exactText("ОШИБКА: Вы не ввели имя пользователя."));
        CustomLogger.logger.info("ok");
        return this;
    }

    @Step("Verify the validation error is displayed when the Password field is empty")
    public LoginPage verifyErrorPasswordFieldIsEmpty() {
        $(byXpath(loginPageLocators.error), 1).shouldBe(visible)
                .shouldHave(exactText("ОШИБКА: Вы не ввели пароль."));
        CustomLogger.logger.info("ok");
        return this;
    }

    @Step("Verify the validation error is displayed when unknown user name is entered")
    public LoginPage verifyErrorUnknownUserNameIsEntered() {
        $(byXpath(loginPageLocators.error)).shouldBe(visible)
                .shouldHave(exactText("Неизвестное имя пользователя. " +
                        "Перепроверьте или попробуйте ваш адрес email."));
        CustomLogger.logger.info("ok");
        return this;
    }

    @Step("Verify the validation error is displayed when unknown user email address is entered")
    public LoginPage verifyErrorUnknownUserEmailAddressIsEntered() {
        $(byXpath(loginPageLocators.error)).shouldBe(visible)
                .shouldHave(exactText("Неизвестный адрес email. " +
                        "Перепроверьте или попробуйте ваше имя пользователя."));
        CustomLogger.logger.info("ok");
        return this;
    }

    @Step("Verify the validation error is displayed when incorrect password is entered - Login with login name")
    public LoginPage verifyErrorIncorrectPasswordIsEnteredLoginWithLoginName(String loginName) {
        $(byXpath(loginPageLocators.error)).shouldBe(visible)
                .shouldHave(exactText("ОШИБКА: Введённый вами пароль пользователя "
                        + loginName + " неверен. Забыли пароль?"));
        CustomLogger.logger.info("ok");
        return this;
    }

    @Step("Verify the validation error is displayed when incorrect password is entered - Login with email")
    public LoginPage verifyErrorIncorrectPasswordIsEnteredLoginWithUserEmail(String email) {
        $(byXpath(loginPageLocators.error)).shouldBe(visible)
                .shouldHave(exactText("ОШИБКА: Введённый вами пароль для адреса "
                        + email + " неверен. Забыли пароль?"));
        CustomLogger.logger.info("ok");
        return this;
    }
}
