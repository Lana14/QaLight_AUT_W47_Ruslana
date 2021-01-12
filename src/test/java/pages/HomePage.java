package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static steps.CommonSteps.checkUrl;

public class HomePage {
    String signInMenuItem = "//a/span[text()='Sign in']";
    String hello = "//a[text()='Привет, ']";
    String userName = "//li[@id='wp-admin-bar-my-account']/a/span";
    String yourObjectiveMenuItem = "//a/span[text()='Your Objective']";
    String achieveYourGoalMenuItem = "//a/span[text()='Achieve your goal']";
    String chooseYourPackMenuItem = "//a/span[text()='Choose your pack']";
    String pricingMenuItem = "//a/span[text()='Pricing']";

    @Step("Open the Login page")
    public LoginPage openLoginPage() {
        $(byXpath(signInMenuItem)).click();
        return new LoginPage();
    }

    @Step("Verify that the Home page is opened by the registered user")
    public HomePage verifyHomePageIsOpenedByRegisteredUser(String userLoginName) {
        $(byXpath(hello)).shouldBe(visible);
        $(byXpath(userName)).shouldHave(exactText(userLoginName));
        return this;
    }

    @Step("Click the Your Objective menu item")
    public void clickYourObjectiveMenuItem() {
        $(byXpath(yourObjectiveMenuItem)).click();
    }

    @Step("Click the Achieve your goal menu item")
    public void clickAchieveYourGoalMenuItem() {
        $(byXpath(achieveYourGoalMenuItem)).click();
    }

    @Step("Click the Choose your pack menu item")
    public void clickChooseYourPackMenuItem() {
        $(byXpath(chooseYourPackMenuItem)).click();
    }

    @Step("Click the Pricing menu item")
    public void clickPricingMenuItem() {
        $(byXpath(pricingMenuItem)).click();
    }

    @Step("Verify the menu item is not a broken link")
    public void verifyMenuItemIsNotBrokenLink(String link) {
        checkUrl(link, 5);
        $(byXpath("//body")).shouldNotHave(text("404"));
    }
}
