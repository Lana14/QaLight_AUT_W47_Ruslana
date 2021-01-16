package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
   DashboardPageLocators dashboardPageLocators = new DashboardPageLocators();

    @Step("Verify that the Dashboard page is opened after user login")
    public DashboardPage verifyDashboardPageIsOpenedAfterUserLogin(String userLoginName) {
        $(byXpath(dashboardPageLocators.headerTitle)).shouldBe(Condition.visible);
        $(byXpath(dashboardPageLocators.hello)).shouldBe(Condition.visible);
        $(byXpath(dashboardPageLocators.hello)).shouldHave(exactText("Привет, " + userLoginName));
        return this;
    }

    @Step("Logout from the Dashboard page")
    public LoginPage logOut() {
        $(byXpath(dashboardPageLocators.logout)).click();
        return new LoginPage();
    }
}
