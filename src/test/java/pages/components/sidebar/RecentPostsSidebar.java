package pages.components.sidebar;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class RecentPostsSidebar {
    RecentPostsSidebarLocators recentPostsSidebarLocators = new RecentPostsSidebarLocators();

    @Step("Open the recent post")
    public void clickRecentPost(int index) {
        $(byXpath(recentPostsSidebarLocators.recentPost), index).shouldBe(Condition.visible).click();
    }
}
