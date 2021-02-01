package tests;

import org.testng.annotations.Test;
import pages.BasePage;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import static common.utils.GoogleSheetParser.getGoogleSheetValue;

public class RecentPostTests extends BasePage {

    @Test(description = "Verify the recent post is opened")
    void verifyRecentPostIsOpened() {
        recentPostsSidebar.clickRecentPost(0);
        postPage.verifyRecentPostIsOpened(0);
    }

    @Test(description = "Verify details of opened post are displayed")
    void verifyDetailsOfOpenedPost() {
        recentPostsSidebar.clickRecentPost(1);
        postPage.verifyRecentPostIsOpened(1)
                .verifyPostDetails();
    }

    @Test(description = "Get new topics for recent posts")
    void getNewTopicsForRecentPosts() throws IOException, GeneralSecurityException {
        postPage.getNewTopicsForRecentPosts();
    }
}
