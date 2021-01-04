package tests;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import utils.Constants;

import static com.codeborne.selenide.Selenide.open;

public class UserAuthorizationTests extends BasePage {

    @BeforeMethod
    public void openUrl() {
        open(Constants.URL);
    }

    @Test(description = "User can login successfully")
    void logIn() {
        homePage.openLoginPage();
        loginPage.insertUserLoginName(Constants.USER_LOGIN_NAME)
                .insertPassword(Constants.USER_PASSWORD)
                .clickSubmitButton();
        dashboardPage.verifyDashboardPageIsOpenedAfterUserLogin(Constants.USER_LOGIN_NAME);
    }

    @Test(description = "User can logout successfully")
    void logOut() {
        homePage.openLoginPage();
        loginPage.loginAs(Constants.USER_LOGIN_NAME, Constants.USER_PASSWORD)
                .loginOut()
                .verifyLogoutMessageIsDisplayed();
    }

    @AfterMethod
    public void closeWindow() {
    Selenide.closeWindow();
    }
}
