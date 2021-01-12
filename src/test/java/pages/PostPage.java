package pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.*;

public class PostPage {
    String headerTitle = "//h1[text()='Блог']";
    String post = "//h2[@itemprop='headline']";
    String recentPostInSidebar = "//div[@id='recent-posts-2']//a";
    String author = "//a[@rel='author']";
    String date = "//li[@class='meta-date']";
    String category = "//a[@rel='category tag']";
    String comments = "//a[@class='comments-link']";

    @Step ("Verify the recent post is opened")
    public PostPage verifyRecentPostIsOpened(int index) {
        $(byXpath(headerTitle)).shouldBe(visible);
        $(byXpath(post)).shouldBe(visible).shouldHave(exactText($(byXpath(recentPostInSidebar), index).getText()));
        return this;
    }

    @Step ("Verify the post details")
    public PostPage verifyPostDetails() {
        $(byXpath(author)).shouldBe(visible);
        $(byXpath(date)).shouldBe(visible);
        $(byXpath(category)).shouldBe(visible);
        $(byXpath(comments)).shouldBe(visible);
        return this;
    }
}
