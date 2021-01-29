package tests_bs;

import common.test_data.users.User;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class BrowserStackTests extends BrowserStack {

    HomePage homePage = new HomePage();
    LoginPage loginPage = new LoginPage();

    @Test(description = "User can logout successfully")
    void logOut() {
        homePage.openLoginPage();
        loginPage.loginAs(User.NAME.getValue(), User.PASSWORD.getValue())
                .logOut()
                .verifyLogoutMessageIsDisplayed();
    }
}
