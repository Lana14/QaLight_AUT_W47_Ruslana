package tests;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BasePage;
import utils.Constants;

import static com.codeborne.selenide.Selenide.open;

public class SearchTests extends BasePage {

    @BeforeMethod
    public void openUrl() {
        open(Constants.URL);
    }

    @Test(description = "Verified searched value is found successfully")
    void verifySearchedValueIsFound() {
        String foundValue = "Neque";

        search.enterAndSearchValue(foundValue)
                .searchedValueIsFound(foundValue);
    }

    @Test(description = "Verified searched value is not found")
    void verifySearchedValueIsNotFound() {
        String notFoundValue = "Test";

        search.enterAndSearchValue(notFoundValue)
                .searchedValueIsNotFound(notFoundValue);
    }

    @AfterMethod
    public void closeWindow() {
        Selenide.closeWindow();
    }
}
