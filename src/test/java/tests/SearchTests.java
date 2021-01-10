package tests;

import org.testng.annotations.Test;
import pages.BasePage;

public class SearchTests extends BasePage {

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
}
