package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    String headerTitle = "//h1[text()='Войти']";
    String userLogin = "//input[@id='user_login']";
    String userPassword = "//input[@id='user_pass']";
    String registerLink = "//a[text()='Регистрация']";

    @Step("Open the Register page")
    public RegisterPage openRegisterPage() {
        $(byXpath(headerTitle)).shouldBe(Condition.visible);
        $(byXpath(registerLink)).shouldBe(Condition.visible).click();
        return new RegisterPage();
    }
}
