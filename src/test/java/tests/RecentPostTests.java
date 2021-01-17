package tests;

import org.testng.annotations.Test;
import pages.BasePage;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import static common.utils.GoogleSheetParser.getGoogleSheetValue;

public class RecentPostTests extends BasePage {

    @Test(description = "Verify the recent post is opened")
    void verifyRecentPostIsOpened() throws IOException, GeneralSecurityException {
        recentPosts.clickRecentPost(0);
        postPage.verifyRecentPostIsOpened(0);
        String spreadsheetId = "1b0U_SyiPhYDv6ognBEONHkcTZLnfpHh7IaamYy_uYQ8";
        String range = "Exam!A2:I24";
        for (List<Object> asd : getGoogleSheetValue(spreadsheetId, range)) {
            System.out.println(asd);
        }
    }

    @Test(description = "Verify details of opened post are displayed")
    void verifyDetailsOfOpenedPost() {
        recentPosts.clickRecentPost(1);
        postPage.verifyRecentPostIsOpened(1)
                .verifyPostDetails();
    }
}
