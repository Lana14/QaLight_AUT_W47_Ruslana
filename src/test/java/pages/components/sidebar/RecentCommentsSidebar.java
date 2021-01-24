package pages.components.sidebar;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class RecentCommentsSidebar {
    RecentCommentsSidebarLocators recentCommentsSidebarLocators = new RecentCommentsSidebarLocators();

    @Step("Open the recent comment")
    public void clickRecentComment(int index) {
        $(byXpath(recentCommentsSidebarLocators.recentComment), index).shouldBe(Condition.visible).click();
    }
}
