package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static steps.CommonSteps.checkUrl;

public class HomePage {
    HomePageLocators homePageLocators = new HomePageLocators();

    @Step("Open the Login page")
    public LoginPage openLoginPage() {
        $(byXpath(homePageLocators.signInMenuItem)).click();
        return new LoginPage();
    }

    @Step("Verify that the Home page is opened by the registered user")
    public HomePage verifyHomePageIsOpenedByRegisteredUser(String userLoginName) {
        $(byXpath(homePageLocators.hello)).shouldBe(visible);
        $(byXpath(homePageLocators.userName)).shouldHave(exactText(userLoginName));
        return this;
    }

    @Step("Click the Your Objective menu item")
    public void clickYourObjectiveMenuItem() {
        $(byXpath(homePageLocators.yourObjectiveMenuItem)).click();
    }

    @Step("Click the Achieve your goal menu item")
    public void clickAchieveYourGoalMenuItem() {
        $(byXpath(homePageLocators.achieveYourGoalMenuItem)).click();
    }

    @Step("Click the Choose your pack menu item")
    public void clickChooseYourPackMenuItem() {
        $(byXpath(homePageLocators.chooseYourPackMenuItem)).click();
    }

    @Step("Click the Pricing menu item")
    public void clickPricingMenuItem() {
        $(byXpath(homePageLocators.pricingMenuItem)).click();
    }

    @Step("Verify the menu item is not a broken link")
    public void verifyMenuItemIsNotBrokenLink(String link) {
        checkUrl(link, 5);
        $(byXpath("//body")).shouldNotHave(text("404"));
    }
}
