package commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;

//import com.browserstack.utils.BrowserStackDriverMap;
import factoryBrowser.ChromeDriverManager;
import factoryBrowser.EdgeDriverManager;
import factoryBrowser.FirefoxDriverManager;
import factoryBrowser.HChromeDriverManager;
import factoryBrowser.HEdgeDriverManager;
import factoryBrowser.HFirefoxDriverManager;
import factoryBrowser.SafariDriverManager;
import factoryEnvironment.BrowserStackFactory;
import factoryEnvironment.LocalFactory;
import factoryEnvironment.MobileFactory;
import listeners.VerificationFailures;
import models.Product;
import models.ProductMedia;


public class BaseTest {
    private WebDriver driver;
    private static ThreadLocal<WebDriver> tDriver = new ThreadLocal<WebDriver>();
    protected Logger log;

    // Configuration properties
    private static Properties configProperties;
    private static final String CONFIG_FILE_PATH = GlobalConstants.TEST_RESOURCES_PATH + "config.properties";

    static {
        loadConfigProperties();
    }

    public BaseTest() {
        log = LogManager.getLogger(getClass());
    }

    /**
     * Load configuration from config.properties file
     */
    protected static void loadConfigProperties() {
        configProperties = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH)) {
            configProperties.load(fis);
        } catch (IOException e) {
            System.err.println("Warning: Could not load config.properties file. Using environment variables or defaults.");
        }
    }

    /**
     * Get configuration value from environment variable first, then from config.properties, then use default
     * Priority: Environment Variables → System Properties → config.properties → Default values
     */
    protected static String getConfigValue(String envVarName, String propertyName, String defaultValue) {
        // Priority 1: Environment variable
        String value = System.getenv(envVarName);
        if (value != null && !value.isEmpty()) {
            return value;
        }

        // Priority 2: System property
        value = System.getProperty(propertyName);
        if (value != null && !value.isEmpty()) {
            return value;
        }

        // Priority 3: config.properties file
        if (configProperties != null) {
            value = configProperties.getProperty(propertyName);
            if (value != null && !value.isEmpty()) {
                return value;
            }
        }

        // Priority 4: Default value
        return defaultValue;
    }

    // Dùng trong bài Allure Report
    public WebDriver getDriver() {
        return tDriver.get();
    }

    // Thử chỉnh theo chatGPT
    private static Map<String, WebDriver> driverMap = new ConcurrentHashMap<>();

    public WebDriver getDriver(ITestContext context) {
        // Ưu tiên lấy trong ThreadLocal
        if (tDriver.get() != null) return tDriver.get();

        // Fallback lấy theo contextName
        WebDriver driver = driverMap.get(context.getName());
        if (driver == null) {
            driver = driverMap.get(context.getName() + "-" + Thread.currentThread().getId());
        }
        return driver;
    }

    // basic
    public WebDriver getBrowserDriver1(String browserName, String url) {
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
        switch (browser) {
            case CHROME:
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            case HCHROME:
                driver = new ChromeDriver();
                break;
            case HFIREFOX:
                driver = new FirefoxDriver();
                break;
            case HEDGE:
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Please input valid browser name value!");
        }

        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        return driver;
    }

    // Browser Factory Pattern
    public WebDriver getBrowserDriver(String browserName, String url) {
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
        switch (browser) {
            case CHROME:
                driver = new ChromeDriverManager().getBrowserDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriverManager().getBrowserDriver();
                break;
            case EDGE:
                driver = new EdgeDriverManager().getBrowserDriver();
                break;
            case HCHROME:
                driver = new HChromeDriverManager().getBrowserDriver();
                break;
            case HFIREFOX:
                driver = new HFirefoxDriverManager().getBrowserDriver();
                break;
            case HEDGE:
                driver = new HEdgeDriverManager().getBrowserDriver();
                break;
            case SAFARI:
                driver = new SafariDriverManager().getBrowserDriver();
                break;
            default:
                throw new RuntimeException("Please input valid browser name value!");
        }
        driver.get(url);
        if (browser == BrowserList.MOBILE_CHROME || browser == BrowserList.MOBILE_SAFARI) {
            //do nothing
        } else {
            driver.manage().window().maximize();
            if (browserName.toLowerCase().startsWith("h")) {
                driver.manage().window().setSize(new org.openqa.selenium.Dimension(1920, 1080));
            }
        }
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        return driver;
    }

    // Environment Factory Pattern
    public WebDriver getBrowserDriver2(String env, String browserName, String browserVersion, String osName, String osVersion, String url) {
        switch (env) {
            case "local":
                tDriver.set(new LocalFactory(browserName).createDriver());
                break;
            case "browserstack":
//                tDriver.set(BrowserStackDriverMap.getCurrentThreadActiveRemoteWebdriver());
                tDriver.set(new BrowserStackFactory(browserName, browserVersion, osName, osVersion).createDriver());
                break;
            case "mobile":
                tDriver.set(new MobileFactory(browserName, osName).createDriver());
                break;
            default:
                throw new RuntimeException("Please input valid environment name value!");
        }
        WebDriver driver = tDriver.get();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        if (!browserName.toLowerCase().contains("mobile")) {
            driver.manage().window().maximize();
            if (browserName.toLowerCase().startsWith("h")) {
                driver.manage().window().setSize(new org.openqa.selenium.Dimension(1920, 1080));
            }
        }
        driver.get(url);
        return driver;
    }

    // Chỉnh theo chatGPT để chạy parallel với ITestContext
    public WebDriver getBrowserDriver2(String env, String browserName, String browserVersion,
                                       String osName, String osVersion, String url, ITestContext context) {
        WebDriver driver;
        switch (env) {
            case "local":
                driver = new LocalFactory(browserName).createDriver();
                break;
            case "browserstack":
                driver = new BrowserStackFactory(browserName, browserVersion, osName, osVersion).createDriver();
                break;
            case "mobile":
                driver = new MobileFactory(browserName, osName).createDriver();
                break;
            default:
                throw new RuntimeException("Please input valid environment name value!");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        if (!browserName.toLowerCase().contains("mobile")) driver.manage().window().maximize();
        driver.get(url);

        tDriver.set(driver);

        // Tự động xác định loại parallel
        String suiteParallel = context.getSuite().getParallel().toString();

        if ("tests".equalsIgnoreCase(suiteParallel)) {
            // parallel=tests → key theo context name
            driverMap.put(context.getName(), driver);
            System.out.println("Created driver for TEST: " + context.getName() + " on thread " + Thread.currentThread().getId());
        } else if ("classes".equalsIgnoreCase(suiteParallel)) {
            // parallel=classes → key theo thread
            driverMap.put(context.getName() + "-" + Thread.currentThread().getId(), driver);
            System.out.println("Created driver for CLASS: " + context.getName() + " on thread " + Thread.currentThread().getId());
        } else {
            // fallback (no parallel)
            driverMap.put(context.getName(), driver);
            System.out.println("Created driver for SINGLE: " + context.getName() + " on thread " + Thread.currentThread().getId());
        }

        return driver;
    }

    // Chỉnh lại browserstack dùng sdk
    public WebDriver getBrowserDriver3(String env, String browserName, String browserVersion, String osName, String osVersion, String url) {
        log.info("getBrowserDriver3 called with env={}, browserName={}, browserVersion={}, osName={}, osVersion={}, url={}", env, browserName, browserVersion, osName, osVersion, url);
        switch (env == null ? "" : env.toLowerCase()) {
            case "local":
                tDriver.set(new LocalFactory(browserName).createDriver());
                break;
//            case "browserstack":
//                try {
//                    // Try to get driver created by BrowserStack SDK map
//                    WebDriver mapped = BrowserStackDriverMap.getCurrentThreadActiveRemoteWebdriver();
//                    if (mapped == null) {
//                        log.warn("BrowserStackDriverMap returned null for current thread. Falling back to creating RemoteWebDriver via BrowserStackFactory.");
//                        tDriver.set(new BrowserStackFactory(browserName, browserVersion, osName, osVersion).createDriver());
//                    } else {
//                        tDriver.set(mapped);
//                    }
//                } catch (Exception e) {
//                    log.error("Exception while obtaining BrowserStack mapped driver; falling back to BrowserStackFactory", e);
//                    tDriver.set(new BrowserStackFactory(browserName, browserVersion, osName, osVersion).createDriver());
//                }
//                break;
            case "mobile":
                tDriver.set(new MobileFactory(browserName, osName).createDriver());
                break;
            default:
                throw new RuntimeException("Please input valid environment name value!");
        }
        WebDriver driver = tDriver.get();
        if (driver == null) {
            // defensive: provide clear error rather than NPE later
            throw new RuntimeException("Failed to initialize WebDriver: tDriver.get() returned null in getBrowserDriver3. Check BrowserStack SDK initialization or factory configuration.");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        if (!browserName.toLowerCase().contains("mobile")) {
            driver.manage().window().maximize();
            if (browserName.toLowerCase().startsWith("h")) {
                driver.manage().window().setSize(new org.openqa.selenium.Dimension(1920, 1080));
            }
        }
        driver.get(url);
        return driver;
    }


    //Close driver basic (local not thread)
    public void closeBrowserDriver() {
        String cmd = null;
        String browserName = null;
        try {
            if (driver == null) {
                log.warn("closeBrowserDriver called but driver is null");
                return;
            }

            String osName = System.getProperty("os.name");
            log.info("osName = " + osName);
            String browserDriverInstanceName = driver.toString().toLowerCase();
            log.info("browserDriverInstanceName = " + browserDriverInstanceName);

            if (browserDriverInstanceName.contains("chrome")) {
                browserName = "chromedriver";
            } else if (browserDriverInstanceName.contains("firefox")) {
                browserName = "firefoxdriver";
            } else if (browserDriverInstanceName.contains("edge")) {
                browserName = "msedgedriver";
            } else if (browserDriverInstanceName.contains("safari")) {
                browserName = "safaridriver";
            }

            // close driver
            try {
                driver.manage().deleteAllCookies();
            } catch (Exception e) {
                log.warn("Could not delete browser cookies before quit: {}", e.getMessage());
            }
            driver.quit();
            log.info("WebDriver quit successfully");

            // kill driver instance
            if (browserName != null) {
                if (osName.toLowerCase().contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq " + browserName + "*\"";
                } else {
                    cmd = "pkill " + browserName;
                }

                Process p = Runtime.getRuntime().exec(cmd);
                int exitCode = p.waitFor();
                log.info("Driver process cleanup command exit code: {}", exitCode);
            } else {
                log.warn("Could not determine browser driver process name from instance string: {}", browserDriverInstanceName);
            }

        } catch (Exception e) {
            log.error("Exception while closing browser driver", e);
        } finally {
            driver = null;
        }
    }

    // Dùng cho Environment Factory Pattern (threadlocal)
    public void closeBrowserDriver2() {
        WebDriver threadDriver = tDriver.get();
        if (threadDriver == null) {
            log.warn("closeBrowserDriver2 called but tDriver.get() is null");
            return;
        }
        log.info("Thread ID = {} - Driver = {}", Thread.currentThread().getId(), threadDriver);

        String cmd = null;
        String browserName = null;
        try {
            String osName = System.getProperty("os.name");
            log.info("osName = " + osName);
            String browserDriverInstanceName = threadDriver.toString().toLowerCase();
            log.info("browserDriverInstanceName = " + browserDriverInstanceName);

            if (browserDriverInstanceName.contains("chrome")) {
                browserName = "chromedriver";
            } else if (browserDriverInstanceName.contains("firefox")) {
                browserName = "firefoxdriver";
            } else if (browserDriverInstanceName.contains("edge")) {
                browserName = "msedgedriver";
            } else if (browserDriverInstanceName.contains("safari")) {
                browserName = "safaridriver";
            }

            try {
                threadDriver.manage().deleteAllCookies();
            } catch (Exception e) {
                log.warn("Could not delete browser cookies before quit (thread {}): {}",
                        Thread.currentThread().getId(), e.getMessage());
            }

            threadDriver.quit();
            log.info("Thread-local WebDriver quit successfully for thread {}", Thread.currentThread().getId());

            if (browserName != null) {
                if (osName.toLowerCase().contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq " + browserName + "*\"";
                } else {
                    cmd = "pkill " + browserName;
                }
                Process process = Runtime.getRuntime().exec(cmd);
                int exitCode = process.waitFor();
                log.info("Thread {} driver process cleanup command exit code: {}", Thread.currentThread().getId(), exitCode);
            } else {
                log.warn("Browser name could not be determined from driver instance: {}", browserDriverInstanceName);
            }
        } catch (Exception e) {
            log.error("Exception while closing thread-local browser driver", e);
        } finally {
            tDriver.remove();
            if (Thread.currentThread().isInterrupted()) {
                log.warn("Thread {} was interrupted during driver cleanup", Thread.currentThread().getId());
            }
        }
    }


    public int generateRandomNumber() {
        return (int) (Math.random() * 9999);
    }

    public void deleteAllFileInFolder(String folderName) {
        String folderPath = GlobalConstants.PROJECT_PATH + GlobalConstants.SEPARATOR + folderName;
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    boolean deleted = file.delete();
                    if (!deleted) {
                        System.out.println("Khong the xoa file" + file.getName());
                    }
                }
            }
        }
    }

    // hàm này để xuất dữ liệu ra file CSV sau khi crawl data để test
    public void writeProductToCSV(List<Product> products) {
        try (PrintWriter writer = new PrintWriter(new File(GlobalConstants.DATA_TEST_PATH + "product.csv"))) {
            writer.println("ProductId,Name,Price,isActive,Description");
//            String cleanDescription = p.getDescription().replaceAll("[\\r\\n]+", " ").trim();
            for (Product p : products) {
                writer.printf("%s,%s,%s,%s,%s\n",
                        p.getProductId(),
                        escapeCsv(p.getName()),
                        p.getPrice(),
                        p.isActive(),
                        p.getDescription().replaceAll("[\\r\\n]+", " ").trim());
                ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void writeProductMediaToCSV(List<ProductMedia> productMedia) {
        try (PrintWriter writer = new PrintWriter(new File(GlobalConstants.DATA_TEST_PATH + "product_media.csv"))) {
            writer.println("ProductId, MediaType, MediaUrl, isMain");
            for (ProductMedia p : productMedia) {
                writer.printf("\"%s\",\"%s\",\"%s\",\"%s\"\n",
                        p.getProductId(), p.getMediaType(), p.getMediaUrl(), p.isMain());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String escapeCsv(String value) {
        if (value == null) return "";
        return "\"" + value.replace("\"", "\"\"") + "\"";
    }


    @BeforeSuite
    public void beforeSuite() {
        deleteAllFileInFolder("allure-results");
    }


//    @AfterSuite(alwaysRun = true)
//    public void afterSuite() {
//        closeBrowserDriver3();
//    }


//    @AfterSuite(alwaysRun = true)
//    public void killRemainingDrivers() {
//        try {
//            if (System.getProperty("os.name").toLowerCase().contains("win")) {
//                Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
//                Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe /T");
//            } else {
//                Runtime.getRuntime().exec("pkill -f chromedriver");
//                Runtime.getRuntime().exec("pkill -f geckodriver");
//            }
//            System.out.println("🧹 Force killed remaining driver processes");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


//    @AfterTest
//    public void afterTest() {
//        System.out.println("Thread id afterTest: " + Thread.currentThread().getId());
//
//        removeDriverSafely();
//    }


    @AfterClass(alwaysRun = true)
    public void afterClass(ITestContext context) {
        closeBrowserDriver4(context);
    }

    // Theo Copilot
    public static void removeDriver() {
        WebDriver driver = tDriver.get();
        if (driver != null) {
            try {
                driver.manage().deleteAllCookies();
                driver.quit();
                System.out.println("Removed WebDriver instance " + driver.toString() + " for thread ID = " + Thread.currentThread().getId());
            } catch (Exception e) {
                System.out.println("Error while quitting driver: " + e.getMessage());
            }

        } else {
            System.out.println("No WebDriver to remove for thread ID = " + Thread.currentThread().getId());
        }
    }

    public void closeThreadDriver() {
        WebDriver driver = tDriver.get();
        if (driver != null) {
            try {
                driver.manage().deleteAllCookies();
                System.out.println("AfterClass - Thread ID: " + Thread.currentThread().getId() + " - Driver still active: " + driver.toString());
                driver.quit();
            } catch (Exception e) {
                log.warn("Driver cleanup failed: " + e.getMessage());
            }
        } else {
            System.out.println("AfterClass - Driver is null for thread: " + Thread.currentThread().getId());
        }
    }

    public void removeDriverSafely() {
        WebDriver driver = tDriver.get();
        if (driver != null) {
            try {
                driver.manage().deleteAllCookies();
                driver.quit();
                System.out.println("Closed WebDriver instance for thread ID = " + Thread.currentThread().getId());
            } catch (Exception e) {
                System.out.println("Error while quitting driver: " + e.getMessage());
            } finally {
                tDriver.remove();
                System.out.println("Removed WebDriver instance for thread ID = " + Thread.currentThread().getId());
            }

        } else {
            System.out.println("No WebDriver to remove for thread ID = " + Thread.currentThread().getId());
        }
    }

    private static void closeBrowserDriver4(ITestContext context) {
        long threadId = Thread.currentThread().getId();
        String suiteParallel = context.getSuite().getParallel().toString();

        WebDriver driver = null;

        if ("tests".equalsIgnoreCase(suiteParallel)) {
            driver = driverMap.remove(context.getName());
        } else if ("classes".equalsIgnoreCase(suiteParallel)) {
            driver = driverMap.remove(context.getName() + "-" + threadId);
        }

        if (driver == null) driver = tDriver.get();
        tDriver.remove();

        if (driver != null) {
            System.out.println("Closing driver for " + context.getName() + " on thread " + threadId);
            driver.quit();
        } else {
            System.out.println("No WebDriver found for " + context.getName() + " on thread " + threadId);
        }
    }

    //Custome Assert
    protected void verifyTrue(boolean condition){
         try{
            Assert.assertTrue(condition);
        }catch(AssertionError e){
            VerificationFailures.addFailuresForTest(e);
        }
    }

    protected void verifyFalse(boolean condition){

        try{
            Assert.assertFalse(condition);
        }catch(AssertionError e){
            VerificationFailures.addFailuresForTest(e);
        }
    }

    protected void verifyEqual(Object actual, Object expected){
        try{
            Assert.assertEquals(actual, expected);
        }catch (AssertionError e){
            VerificationFailures.addFailuresForTest(e);
        }
    }

}