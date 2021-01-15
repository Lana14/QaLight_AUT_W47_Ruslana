package components.sidebar;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class RecentPosts {
    public static String recentPostInSidebar = "//div[@id='recent-posts-2']//a";

    @Step ("Open the recent post")
    public void clickRecentPost(int index) {
        $(byXpath(recentPostInSidebar), index).shouldBe(Condition.visible).click();
    }
}
