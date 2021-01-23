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
        recentPosts.clickRecentPost(0);
        postPage.verifyRecentPostIsOpened(0);
    }

    @Test(description = "Verify details of opened post are displayed")
    void verifyDetailsOfOpenedPost() {
        recentPosts.clickRecentPost(1);
        postPage.verifyRecentPostIsOpened(1)
                .verifyPostDetails();
    }

    @Test(description = "Get new topics for recent posts")
    void getNewTopicsForRecentPosts() throws IOException, GeneralSecurityException {
        String spreadsheetId = "1ojvfbEPjDeFX2AY_nzTGT-g3n9mZZCjSzzP9MDWSkFw";
        String range = "Sport!A2:A7";
        for (List<Object> asd : getGoogleSheetValue(spreadsheetId, range)) {
            System.out.println(asd);
        }
    }
}
