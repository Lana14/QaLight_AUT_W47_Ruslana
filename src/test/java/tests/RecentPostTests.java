package tests;

import org.testng.annotations.Test;
import pages.BasePage;

public class RecentPostTests extends BasePage {

    @Test(description = "Verify the recent post is opened")
    void verifyRecentPostIsOpened() {
        recentPosts.clickRecentPost(0);
        postPage.verifyRecentPostIsOpened(0);
    }

    @Test(description = "Verify details of opened post are displayed")
    void verifyDetailsOfOpenedPost() {
        recentPosts.clickRecentPost(1);
        postPage.verifyRecentPostIsOpened(1)
                .verifyPostDetails();
    }
}
