package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static logger.CustomLogger.logger;

public class LoginPage {
    String headerTitle = "//h1[text()='Войти']";
    String userLoginName = "//input[@id='user_login']";
    String userPassword = "//input[@id='user_pass']";
    String submitButton = "//button[@name='submit']";
    String registerLink = "//a[text()='Регистрация']";
    String message = "//li[@class='tml-message']";
    String error = "//li[@class='tml-error']";

    @Step("Open the Register page")
    public RegisterPage openRegisterPage() {
        $(byXpath(headerTitle)).shouldBe(Condition.visible);
        $(byXpath(registerLink)).shouldBe(Condition.visible).click();
        return new RegisterPage();
    }

    @Step("Insert user login")
    public LoginPage insertUserLoginName(String loginName) {
        $(byXpath(headerTitle)).shouldBe(Condition.visible);
        $(byXpath(userLoginName)).sendKeys(loginName);
        return this;
    }

    @Step("Insert password")
    public LoginPage insertPassword(String password) {
        $(byXpath(userPassword)).sendKeys(password);
        return this;
    }

    @Step("Submit the authorization credentials")
    public DashboardPage clickSubmitButton() {
        $(byXpath(submitButton)).click();
        return new DashboardPage();
    }

    @Step("Login as an authorized user")
    public DashboardPage loginAs(String userName, String password) {
        logger.info("Login as {}", userName);
        insertUserLoginName(userName)
                .insertPassword(password)
                .clickSubmitButton();
        return new DashboardPage();
    }

    @Step("Verify that the logout message is displayed")
    public LoginPage verifyLogoutMessageIsDisplayed() {
        $(byXpath(headerTitle)).shouldBe(Condition.visible);
        $(byXpath(message)).shouldBe(Condition.visible)
                .shouldHave(exactText("Вы вышли из системы."));
        return this;
    }

    @Step("Verify the validation error is displayed when the UserLoginName field is empty")
    public LoginPage verifyErrorUserLoginNameFieldIsEmpty() {
        $(byXpath(error), 0).shouldBe(Condition.visible)
                .shouldHave(exactText("ОШИБКА: Вы не ввели имя пользователя."));
        return this;
    }

    @Step("Verify the validation error is displayed when the Password field is empty")
    public LoginPage verifyErrorPasswordFieldIsEmpty() {
        $(byXpath(error), 1).shouldBe(Condition.visible)
                .shouldHave(exactText("ОШИБКА: Вы не ввели пароль."));
        return this;
    }

    @Step("Verify the validation error is displayed when unknown user name is entered")
    public LoginPage verifyErrorUnknownUserNameIsEntered() {
        $(byXpath(error)).shouldBe(Condition.visible)
                .shouldHave(exactText("Неизвестное имя пользователя. Перепроверьте или попробуйте ваш адрес email."));
        return this;
    }

    @Step("Verify the validation error is displayed when unknown user email address is entered")
    public LoginPage verifyErrorUnknownUserEmailAddressIsEntered() {
        $(byXpath(error)).shouldBe(Condition.visible)
                .shouldHave(exactText("Неизвестный адрес email. Перепроверьте или попробуйте ваше имя пользователя."));
        return this;
    }

    @Step("Verify the validation error is displayed when incorrect password is entered - Login with login name")
    public LoginPage verifyErrorIncorrectPasswordIsEnteredLoginWithLoginName(String loginName) {
        $(byXpath(error)).shouldBe(Condition.visible)
                .shouldHave(exactText("ОШИБКА: Введённый вами пароль пользователя " + loginName + " неверен. Забыли пароль?"));
        return this;
    }

    @Step("Verify the validation error is displayed when incorrect password is entered - Login with email")
    public LoginPage verifyErrorIncorrectPasswordIsEnteredLoginWithUserEmail(String email) {
        $(byXpath(error)).shouldBe(Condition.visible)
                .shouldHave(exactText("ОШИБКА: Введённый вами пароль для адреса " + email + " неверен. Забыли пароль?"));
        return this;
    }
}
