package pages.components.sidebar;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class Search {
    String search = "//input[@type='search']";
    String topSearchResult = "(//div[@class='search-entry-content clr'])[1]";
    String pageContent = "//div[@class='page-content']";

    @Step("Enter value into the Search field")
    public Search enterAndSearchValue(String text) {
        $(byXpath(search)).shouldBe(visible);
        $(byXpath(search)).sendKeys(text);
        $(byXpath(search)).sendKeys(Keys.ENTER);
        return this;
    }

    @Step("Verify search result list contains results with the searched value")
    public Search searchedValueIsFound(String searchValue) {
        $(byXpath(topSearchResult)).shouldBe(visible)
                .shouldHave(text(searchValue));
        return this;
    }

    @Step("Verify search result list does not contain results with the searched value")
    public Search searchedValueIsNotFound(String searchValue) {
        $(byXpath(pageContent)).shouldBe(visible)
                .shouldHave(exactText("Извините, но по вашему запросу ничего не найдено. " +
                        "Пожалуйста, попробуйте снова, пользуясь другими ключевыми словами."));
        return this;
    }
}
