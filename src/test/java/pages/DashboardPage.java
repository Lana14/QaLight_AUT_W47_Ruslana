package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    String headerTitle = "//h1[text()='Консоль']";
    String hello = "//p[@class='tml-dashboard-greeting']";
    String logout = "//ul[@class='tml-dashboard-links']//a[text()='Выйти']";

    @Step("Verify that the Dashboard page is opened after user login")
    public DashboardPage verifyDashboardPageIsOpenedAfterUserLogin(String userLoginName) {
        $(byXpath(headerTitle)).shouldBe(Condition.visible);
        $(byXpath(hello)).shouldBe(Condition.visible);
        $(byXpath(hello)).shouldHave(exactText("Привет, " + userLoginName));
        return this;
    }

    @Step("Logout from the Dashboard page")
    public LoginPage logOut() {
        $(byXpath(logout)).click();
        return new LoginPage();
    }
}
