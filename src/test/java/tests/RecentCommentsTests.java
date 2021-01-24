package tests;

import common.test_data.users.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;
import pages.BasePage;

public class RecentCommentsTests extends BasePage {

    @Test(description = "Verify the recent comment can be opened from the sidebar")
    void verifyRecentPostIsOpened() {
        recentCommentsSidebar.clickRecentComment(0);
        postPage.verifyRecentCommentIsDisplayedForRelevantPost(0);
    }

    @Test(description = "Verify logged in user can add a comment")
    void verifyLoggedInUserCanAddComment() {
        String testComment = "Test comment " + RandomStringUtils.randomAlphabetic(10);

        homePage.openLoginPage();
        loginPage.loginAs(User.NAME.getValue(), User.PASSWORD.getValue());
        dashboardPage.verifyDashboardPageIsOpenedAfterUserLogin(User.NAME.getValue());
        recentCommentsSidebar.clickRecentComment(0);
        postPage.verifyAddCommentSectionIsDisplayed()
                .addComment(testComment)
                .verifyAddedCommentIsDisplayed(testComment);
    }
}
