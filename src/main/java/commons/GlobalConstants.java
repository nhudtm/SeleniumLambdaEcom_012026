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
    public static final String PROPERTIES_FILE_PATH = TEST_RESOURCES_PATH + "config.properties";
    
    // Jira Configuration - using BaseTest's getConfigValue method
    public static final String JIRA_SITE_URL = utils.PropertiesConfig.getProp("jira.site.url");
    public static final String JIRA_USERNAME = utils.PropertiesConfig.getProp("jira.username");
    public static final String JIRA_API_KEY = utils.PropertiesConfig.getProp("jira.api.key");
    public static final String JIRA_PROJECT_KEY = utils.PropertiesConfig.getProp("jira.project.key");
    public static final String BASE_PATH = "/rest/api/3/project/";
    public static final String CREATE_ISSUE_PATH = "/rest/api/3/issue";
    public static final String SEARCH_ISSUE_PATH = "/rest/api/3/search";

    // Application URL
    public static final String DEV_URL = utils.PropertiesConfig.getProp("dev.url");

    // BrowserStack Configuration
    public static final String BROWSER_STACK_USERNAME = utils.PropertiesConfig.getProp( "browserstack.username");
    public static final String BROWSER_STACK_ACCESS_KEY = utils.PropertiesConfig.getProp( "browserstack.access.key");
    public static final String BROWSERSTACK_URL = "https://" + BROWSER_STACK_USERNAME + ":"
            + BROWSER_STACK_ACCESS_KEY
            + "@hub.browserstack.com/wd/hub";
}
