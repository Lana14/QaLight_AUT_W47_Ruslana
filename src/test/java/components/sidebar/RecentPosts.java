package components.sidebar;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class RecentPosts {
    String recentPost = "//div[@id='recent-posts-2']//a";

    @Step ("Open the recent post")
    public RecentPosts clickRecentPost(int index) {
        $(byXpath(recentPost), index).shouldBe(Condition.visible).click();
        return this;
    }
}
