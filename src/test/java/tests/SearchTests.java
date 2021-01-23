package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BasePage;

public class SearchTests extends BasePage {

    @DataProvider(name = "SearchDataProvider")
    public Object[][] getDataFromDataProvider() {
        return new Object[][]
                {
                        {"Neque"},
                        {"Litora torqent per conubia"},
                        {"Prae"},
                        {"auctor"},
                        {"magna augue"}
                };
    }

    @Test(description = "Verified searched value is found successfully", dataProvider = "SearchDataProvider")
    void verifySearchedValueIsFound(String value) {
        search.enterAndSearchValue(value)
                .searchedValueIsFound(value);
    }

    @Test(description = "Verified searched value is not found")
    void verifySearchedValueIsNotFound() {
        String notFoundValue = "Test";

        search.enterAndSearchValue(notFoundValue)
                .searchedValueIsNotFound(notFoundValue);
    }
}
