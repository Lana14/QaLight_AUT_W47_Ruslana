package pages;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    String signIn = "//span[text()='Sign in']";
    String hello = "//a[text()='Привет, ']";
    String userName = "//li[@id='wp-admin-bar-my-account']/a/span";
    String yourObjectiveMenuItem = "//a/span[text()='Your Objective']";
    String achieveYourGoalMenuItem = "//a/span[text()='Achieve your goal']";
    String chooseYourPackMenuItem = "//a/span[text()='Choose your pack']";
    String pricingMenuItem = "//a/span[text()='Pricing']";
    String signInMenuItem = "//a/span[text()='Sign in']";

    @Step("Open the Login page")
    public LoginPage openLoginPage() {
        $(byXpath(signIn)).click();
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

    @Step("Verify the Your Objective menu item is not a broken link")
    public void verifyYourObjectiveMenuItemIsNotBrokenLink() {
        String h2 = "//h2[text()='Фитнес']";
        String body = "//body";

        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(),
                "https://5element.ua/");
        $(byXpath(body)).shouldNotHave(text("404"));
        $(byXpath(h2)).shouldBe(visible);
    }

    @Step("Click the Achieve your goal menu item")
    public void clickAchieveYourGoalMenuItem() {
        $(byXpath(achieveYourGoalMenuItem)).click();
    }

    @Step("Verify the Achieve your goal menu item is not a broken link")
    public void verifyAchieveYourGoalMenuItemIsNotBrokenLink() {
        String body = "//body";

        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(),
                "https://5element.ua/about/special_offer/");
        $(byXpath(body)).shouldNotHave(text("404"));
    }

    @Step("Click the Choose your pack menu item")
    public void clickChooseYourPackMenuItem() {
        $(byXpath(chooseYourPackMenuItem)).click();
    }

    @Step("Verify the Choose your pack menu item is not a broken link")
    public void verifyChooseYourPackMenuItemIsNotBrokenLink() {
        String body = "//body";

        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(),
                "https://5element.ua/shedule/");
        $(byXpath(body)).shouldNotHave(text("404"));
    }

    @Step("Click the Pricing menu item")
    public void clickPricingMenuItem() {
        $(byXpath(pricingMenuItem)).click();
    }

    @Step("Verify the Pricing menu item is not a broken link")
    public void verifyPricingMenuItemIsNotBrokenLink() {
        String body = "//body";

        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(),
                "https://5element.ua/klubnye-karty/");
        $(byXpath(body)).shouldNotHave(text("404"));
    }

    @Step("Verify the Sign in menu item is not a broken link")
    public void verifySignInMenuItemIsNotBrokenLink() {
        String body = "//body";

        Assert.assertEquals(WebDriverRunner.getWebDriver().getCurrentUrl(),
                "https://it-platforma.website/login/");
        $(byXpath(body)).shouldNotHave(text("404"));
    }
}
