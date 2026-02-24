package commons;

public class GlobalConstants {
    public static final int LONG_TIMEOUT = 30;
    public static final int SHORT_TIMEOUT = 10;

    public static final String PROJECT_PATH = System.getProperty("user.dir");
    public static final String SEPARATOR = System.getProperty("file.separator");
    public static final String UPLOAD_PATH = PROJECT_PATH + SEPARATOR + "uploadFiles" + SEPARATOR;
    public static final String DATA_TEST_PATH = PROJECT_PATH + SEPARATOR + "dataTests" + SEPARATOR;
    public static final String DEVICE_CONFIG_PATH = PROJECT_PATH + SEPARATOR + "src" + SEPARATOR + "test" + SEPARATOR + "resources" + SEPARATOR + "deviceConfig.properties";
    public static final String OS_NAME = System.getProperty("os.name").toLowerCase();
    public static final String TEST_RESOURCES_PATH = PROJECT_PATH + SEPARATOR + "src" + SEPARATOR + "test" + SEPARATOR + "resources" + SEPARATOR;

    // Jira Configuration - using BaseTest's getConfigValue method
    public static final String JIRA_SITE_URL = BaseTest.getConfigValue("JIRA_SITE_URL", "jira.site.url", "https://automationtest.atlassian.net");
    public static final String JIRA_USERNAME = BaseTest.getConfigValue("JIRA_USERNAME", "jira.username", "");
    public static final String JIRA_API_KEY = BaseTest.getConfigValue("JIRA_API_KEY", "jira.api.key", "");
    public static final String JIRA_PROJECT_KEY = BaseTest.getConfigValue("JIRA_PROJECT_KEY", "jira.project.key", "SCRUM");
    public static final String BASE_PATH = "/rest/api/3/project/";
    public static final String CREATE_ISSUE_PATH = "/rest/api/3/issue";
    public static final String SEARCH_ISSUE_PATH = "/rest/api/3/search";

    // Application URL
    public static final String DEV_URL = BaseTest.getConfigValue("DEV_URL", "dev.url", "https://ecommerce-playground.lambdatest.io");

    // BrowserStack Configuration
    public static final String BROWSER_STACK_USERNAME = BaseTest.getConfigValue("BROWSERSTACK_USERNAME", "browserstack.username", "");
    public static final String BROWSER_STACK_ACCESS_KEY = BaseTest.getConfigValue("BROWSERSTACK_ACCESS_KEY", "browserstack.access.key", "");
    public static final String BROWSERSTACK_URL = "https://" + BROWSER_STACK_USERNAME + ":"
            + BROWSER_STACK_ACCESS_KEY
            + "@hub.browserstack.com/wd/hub";
}
