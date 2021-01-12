package pages;

import components.sidebar.RecentPosts;
import components.sidebar.Search;
import runners.LocalRunner;

public class BasePage extends LocalRunner {
    public HomePage homePage = new HomePage();
    public LoginPage loginPage = new LoginPage();
    public RegisterPage registerPage = new RegisterPage();
    public DashboardPage dashboardPage = new DashboardPage();
    public Search search = new Search();
    public RecentPosts recentPosts = new RecentPosts();
    public PostPage postPage = new PostPage();
}
