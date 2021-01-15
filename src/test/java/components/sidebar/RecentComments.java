package components.sidebar;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class RecentComments {
    public static String recentCommentInSidebar = "//li[@class='recentcomments']//a";

    @Step("Open the recent comment")
    public void clickRecentComment(int index) {
        $(byXpath(recentCommentInSidebar), index).shouldBe(Condition.visible).click();
    }
}
