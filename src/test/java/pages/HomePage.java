package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    String signIn = "//span[text()='Sign in']";
    String hello = "//a[text()='Привет, ']";
    String userName = "//li[@id='wp-admin-bar-my-account']/a/span";

    @Step("Open the Login page")
    public LoginPage openLoginPage() {
        $(byXpath(signIn)).click();
        return new LoginPage();
    }

    @Step("Verify that the Home page is opened by the registered user")
    public HomePage verifyHomePageIsOpenedByRegisteredUser(String userLoginName){
        $(byXpath(hello)).shouldBe(Condition.visible);
        $(byXpath(userName)).shouldHave(exactText(userLoginName));
        return this;
    }
}
