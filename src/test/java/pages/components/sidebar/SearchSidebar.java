package pages.components.sidebar;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class SearchSidebar {
    SearchSidebarLocators searchSidebarLocators = new SearchSidebarLocators();

    @Step("Enter value into the Search field")
    public SearchSidebar enterAndSearchValue(String text) {
        $(byXpath(searchSidebarLocators.search)).shouldBe(visible);
        $(byXpath(searchSidebarLocators.search)).sendKeys(text);
        $(byXpath(searchSidebarLocators.search)).sendKeys(Keys.ENTER);
        return this;
    }

    @Step("Verify search result list contains results with the searched value")
    public SearchSidebar searchedValueIsFound(String searchValue) {
        $(byXpath(searchSidebarLocators.topSearchResult)).shouldBe(visible)
                .shouldHave(text(searchValue));
        return this;
    }

    @Step("Verify search result list does not contain results with the searched value")
    public SearchSidebar searchedValueIsNotFound() {
        $(byXpath(searchSidebarLocators.pageContent)).shouldBe(visible)
                .shouldHave(exactText("Извините, но по вашему запросу ничего не найдено. " +
                        "Пожалуйста, попробуйте снова, пользуясь другими ключевыми словами."));
        return this;
    }
}
