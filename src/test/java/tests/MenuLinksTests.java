package tests;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import utils.Constants;

import static com.codeborne.selenide.Selenide.open;

public class MenuLinksTests extends BasePage {

    @BeforeMethod
    public void openUrl() {
        open(Constants.URL);
    }

    @Test(description = "Verify the Your Objective menu item is not a broken link")
    void VerifyYourObjectiveMenuItemIsNotBrokenLink() {
        homePage.clickYourObjectiveMenuItem();
        homePage.verifyMenuItemIsNotBrokenLink("https://5element.ua/");
    }

    @Test(description = "Verify the Achieve your goal menu item is not a broken link")
    void VerifyAchieveYourGoalMenuItemIsNotBrokenLink() {
        homePage.clickAchieveYourGoalMenuItem();
        homePage.verifyMenuItemIsNotBrokenLink("https://5element.ua/about/special_offer/");
    }

    @Test(description = "Verify the Choose your pack menu item is not a broken link")
    void VerifyChooseYourPackMenuItemIsNotBrokenLink() {
        homePage.clickChooseYourPackMenuItem();
        homePage.verifyMenuItemIsNotBrokenLink("https://5element.ua/shedule/");
    }

    @Test(description = "Verify the Pricing menu item is not a broken link")
    void VerifyPricingMenuItemIsNotBrokenLink() {
        homePage.clickPricingMenuItem();
        homePage.verifyMenuItemIsNotBrokenLink("https://5element.ua/klubnye-karty/");
    }

    @Test(description = "Verify the SignIn menu item is not a broken link")
    void VerifySignInMenuItemIsNotBrokenLink() {
        homePage.openLoginPage();
        homePage.verifyMenuItemIsNotBrokenLink("https://it-platforma.website/login/");
    }

    @AfterMethod
    public void closeWindow() {
        Selenide.closeWindow();
    }
}
