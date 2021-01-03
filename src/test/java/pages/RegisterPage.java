package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class RegisterPage {
    String headerTitle = "//h1[text()='Регистрация']";
    String userLoginName = "//input[@id='user_login']";
    String userEmail = "//input[@id='user_email']";
    String userPassword = "//input[@id='pass1']";
    String userPasswordConfirmation = "//input[@id='pass2']";
    String submitButton = "//button[@name='submit']";
    SelenideElement error = $(byXpath("//li[@class='tml-error']//strong[text()='ОШИБКА']"));
    String errorMessage = "//li[@class='tml-error']";

    @Step("Insert user login")
    public RegisterPage insertUserLoginName(String loginName) {
        $(byXpath(headerTitle)).shouldBe(Condition.visible);
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

    @Step("Confirm password")
    public HomePage clickSubmitButton() {
        $(byXpath(submitButton)).click();
        return new HomePage();
    }

    @Step("Verify that the duplicate email address error is displayed")
    public RegisterPage duplicateEmailAddressErrorIsDisplayed() {
        String duplicateEmailAddressErrorMessage = "ОШИБКА: Этот email уже зарегистрирован. Пожалуйста, введите другой.";
        error.shouldBe(Condition.visible);
        Assert.assertEquals(WebDriverRunner.getWebDriver().findElement(byXpath(errorMessage)).getText(), duplicateEmailAddressErrorMessage);
        return this;
    }
}
