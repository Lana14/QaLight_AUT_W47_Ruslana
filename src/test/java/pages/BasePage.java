package pages;

import pages.components.sidebar.RecentCommentsSidebar;
import pages.components.sidebar.RecentPostsSidebar;
import pages.components.sidebar.SearchSidebar;
import common.runners.LocalRunner;

public class BasePage extends LocalRunner {
    public HomePage homePage = new HomePage();
    public LoginPage loginPage = new LoginPage();
    public RegisterPage registerPage = new RegisterPage();
    public DashboardPage dashboardPage = new DashboardPage();
    public SearchSidebar searchSidebar = new SearchSidebar();
    public RecentPostsSidebar recentPostsSidebar = new RecentPostsSidebar();
    public RecentCommentsSidebar recentCommentsSidebar = new RecentCommentsSidebar();
    public PostPage postPage = new PostPage();
}
