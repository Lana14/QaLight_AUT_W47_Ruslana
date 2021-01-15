package pages;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.*;
import static components.sidebar.RecentComments.recentCommentInSidebar;
import static components.sidebar.RecentPosts.recentPostInSidebar;

public class PostPage {
    String headerTitle = "//h1[text()='Блог']";
    String post = "//h2[@itemprop='headline']";
    String author = "//a[@rel='author']";
    String date = "//li[@class='meta-date']";
    String category = "//a[@rel='category tag']";
    String commentsTotalNumber = "//a[@class='comments-link']";
    String lastComment = "//ol[@class='comment-list']/li[@class='comment-container'][last()]";
    String addCommentSection = "//div[@id='respond']";
    String commentTextarea = "//textarea[@class='textarea-comment']";
    String submitComment = "//input[@name='submit']";
    String lastCommentContent = "//ol[@class='comment-list']/li[@class='comment-container']" +
            "[last()]//div[@class='comment-entry']/div[@class='comment-content']";

    @Step("Verify the recent post is opened")
    public PostPage verifyRecentPostIsOpened(int index) {
        $(byXpath(headerTitle)).shouldBe(visible);
        $(byXpath(post)).shouldBe(visible)
                .shouldHave(exactText($(byXpath(recentPostInSidebar), index).getText()));
        return this;
    }

    @Step("Verify the post details")
    public PostPage verifyPostDetails() {
        $(byXpath(author)).shouldBe(visible);
        $(byXpath(date)).shouldBe(visible);
        $(byXpath(category)).shouldBe(visible);
        $(byXpath(commentsTotalNumber)).shouldBe(visible);
        return this;
    }

    @Step("Verify the recent comment is displayed for the relevant post")
    public PostPage verifyRecentCommentIsDisplayedForRelevantPost(int index) {
        $(byXpath(headerTitle)).shouldBe(visible);
        $(byXpath(post)).shouldBe(visible)
                .shouldHave(exactText($(byXpath(recentCommentInSidebar), index).getText()));
        $(byXpath(lastComment)).shouldBe(visible);
        return this;
    }

    @Step("Verify the add comment section is displayed")
    public PostPage verifyAddCommentSectionIsDisplayed() {
        $(byXpath(addCommentSection)).shouldBe(visible);
        return this;
    }

    @Step("Add a comment")
    public PostPage addComment(String text) {
        $(byXpath(commentTextarea)).sendKeys(text);
        $(byXpath(submitComment)).click();
        return this;
    }

    @Step("Verify the added comment is displayed")
    public PostPage verifyAddedCommentIsDisplayed(String text) {
        Assert.assertEquals($(byXpath(lastCommentContent)).getText(), text);
        return this;
    }
}
