package pages;

import io.qameta.allure.Step;
import org.testng.Assert;
import pages.components.sidebar.RecentCommentsSidebarLocators;
import pages.components.sidebar.RecentPostsSidebarLocators;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.*;

public class PostPage {
    PostPageLocators postPageLocators = new PostPageLocators();
    RecentPostsSidebarLocators recentPostsSidebarLocators = new RecentPostsSidebarLocators();
    RecentCommentsSidebarLocators recentCommentsSidebarLocators = new RecentCommentsSidebarLocators();

    @Step("Verify the recent post is opened")
    public PostPage verifyRecentPostIsOpened(int index) {
        $(byXpath(postPageLocators.headerTitle)).shouldBe(visible);
        $(byXpath(postPageLocators.post)).shouldBe(visible)
                .shouldHave(exactText($(byXpath(recentPostsSidebarLocators.recentPost), index).getText()));
        return this;
    }

    @Step("Verify the post details")
    public PostPage verifyPostDetails() {
        $(byXpath(postPageLocators.author)).shouldBe(visible);
        $(byXpath(postPageLocators.date)).shouldBe(visible);
        $(byXpath(postPageLocators.category)).shouldBe(visible);
        $(byXpath(postPageLocators.commentsTotalNumber)).shouldBe(visible);
        return this;
    }

    @Step("Verify the recent comment is displayed for the relevant post")
    public PostPage verifyRecentCommentIsDisplayedForRelevantPost(int index) {
        $(byXpath(postPageLocators.headerTitle)).shouldBe(visible);
        $(byXpath(postPageLocators.post)).shouldBe(visible)
                .shouldHave(exactText($(byXpath(recentCommentsSidebarLocators.recentComment), index).getText()));
        $(byXpath(postPageLocators.lastComment)).shouldBe(visible);
        return this;
    }

    @Step("Verify the add comment section is displayed")
    public PostPage verifyAddCommentSectionIsDisplayed() {
        $(byXpath(postPageLocators.addCommentSection)).shouldBe(visible);
        return this;
    }

    @Step("Add a comment")
    public PostPage addComment(String text) {
        $(byXpath(postPageLocators.commentTextarea)).sendKeys(text);
        $(byXpath(postPageLocators.submitComment)).click();
        return this;
    }

    @Step("Verify the added comment is displayed")
    public PostPage verifyAddedCommentIsDisplayed(String text) {
        Assert.assertEquals($(byXpath(postPageLocators.lastCommentContent)).getText(), text);
        return this;
    }
}
