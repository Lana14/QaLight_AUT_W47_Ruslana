package pages;

import common.logger.CustomLogger;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static common.steps.CommonSteps.checkUrl;

public class HomePage {
    HomePageLocators homePageLocators = new HomePageLocators();

    @Step("Open the Login page")
    public LoginPage openLoginPage() {
        $(byXpath(homePageLocators.signInMenuItem)).click();
        CustomLogger.logger.info("ok");
        return new LoginPage();
    }

    @Step("Verify that the Home page is opened by the registered user")
    public HomePage verifyHomePageIsOpenedByRegisteredUser(String userLoginName) {
        $(byXpath(homePageLocators.hello)).shouldBe(visible);
        $(byXpath(homePageLocators.userName)).shouldHave(exactText(userLoginName));
        CustomLogger.logger.info("ok");
        return this;
    }

    @Step("Click the Your Objective menu item")
    public void clickYourObjectiveMenuItem() {
        $(byXpath(homePageLocators.yourObjectiveMenuItem)).click();
        CustomLogger.logger.info("ok");
    }

    @Step("Click the Achieve your goal menu item")
    public void clickAchieveYourGoalMenuItem() {
        $(byXpath(homePageLocators.achieveYourGoalMenuItem)).click();
        CustomLogger.logger.info("ok");
    }

    @Step("Click the Choose your pack menu item")
    public void clickChooseYourPackMenuItem() {
        $(byXpath(homePageLocators.chooseYourPackMenuItem)).click();
        CustomLogger.logger.info("ok");
    }

    @Step("Click the Pricing menu item")
    public void clickPricingMenuItem() {
        $(byXpath(homePageLocators.pricingMenuItem)).click();
        CustomLogger.logger.info("ok");
    }

    @Step("Verify the menu item is not a broken link")
    public void verifyMenuItemIsNotBrokenLink(String link) {
        checkUrl(link, 5);
        $(byXpath("//body")).shouldNotHave(text("404"));
        CustomLogger.logger.info("ok");
    }
}
