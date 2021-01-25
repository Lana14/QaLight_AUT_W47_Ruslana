package common.utils;

public class Global {
    public static final String USER_EMAIL_PREFIX = "automation_test+";
    public static final String USER_EMAIL_SUFFIX = "@gmail.com";

    public static String globalDevice = "";
    public static String globalBrowserName = "";

    public static void resetGlobalVariablesAfterMethod() {
        globalDevice = "";
        globalBrowserName = "";
    }
}
