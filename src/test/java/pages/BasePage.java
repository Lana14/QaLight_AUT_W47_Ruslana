package pages;

import components.sidebar.RecentComments;
import components.sidebar.RecentPosts;
import components.sidebar.Search;
import common.runners.LocalRunner;

public class BasePage extends LocalRunner {
    public HomePage homePage = new HomePage();
    public LoginPage loginPage = new LoginPage();
    public RegisterPage registerPage = new RegisterPage();
    public DashboardPage dashboardPage = new DashboardPage();
    public Search search = new Search();
    public RecentPosts recentPosts = new RecentPosts();
    public RecentComments recentComments = new RecentComments();
    public PostPage postPage = new PostPage();
}
