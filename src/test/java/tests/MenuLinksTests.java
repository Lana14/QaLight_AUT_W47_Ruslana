package tests;

import org.testng.annotations.Test;
import pages.BasePage;

public class MenuLinksTests extends BasePage {

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
}
