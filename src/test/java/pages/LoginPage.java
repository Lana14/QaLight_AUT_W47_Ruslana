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
    public DashboardPage loginAs(String username, String password) {
        logger.info("Login as {}", username);
        insertUserLoginName(username)
                .insertPassword(password)
                .clickSubmitButton();
        return new DashboardPage();
    }

    @Step("Verify that the logout message is displayed")
    public LoginPage verifyLogoutMessageIsDisplayed(){
        $(byXpath(headerTitle)).shouldBe(Condition.visible);
        $(byXpath(message)).shouldBe(Condition.visible);
        $(byXpath(message)).shouldHave(exactText("Вы вышли из системы."));
        return this;
    }
}
