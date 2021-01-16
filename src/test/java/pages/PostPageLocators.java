package pages;

public class PostPageLocators {
    String headerTitle = "//h1[text()='Блог']";
    String post = "//h2[@itemprop='headline']";
    String author = "//a[@rel='author']";
    String date = "//li[@class='meta-date']";
    String category = "//a[@rel='category tag']";
    String commentsTotalNumber = "//a[@class='comments-link']";
    String lastComment = "//ol[@class='comment-list']/li[@class='comment-container'][last()]";
    String addCommentSection = "//div[@id='respond']";
    String commentTextarea = "//textarea[@class='textarea-comment']";
    String submitComment = "//input[@name='submit']";
    String lastCommentContent = "//ol[@class='comment-list']/li[@class='comment-container']" +
            "[last()]//div[@class='comment-entry']/div[@class='comment-content']";
}
