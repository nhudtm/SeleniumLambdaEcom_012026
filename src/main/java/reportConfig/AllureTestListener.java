package reportConfig;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import commons.BaseTest;
import io.qameta.allure.Attachment;

public class AllureTestListener extends BaseTest implements ITestListener {
    private static final Logger logger = LogManager.getLogger(AllureTestListener.class);
    private static int testCount = 0;
    private static int passCount = 0;
    private static int failCount = 0;
    private static long suiteStartTime = 0;

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    private static String getTestClassName(ITestResult iTestResult) {
        return iTestResult.getTestClass().getName().replace("tests.", "");
    }

    @Attachment(value="Screenshot of {0}", type="image/png")
    public static byte[] saveScreenshotPNG(String testName, WebDriver driver) {
        if(driver!=null) {
            System.out.println("Taking screenshot for test: " + testName);

            return (byte[]) ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } else {
            System.out.println("WebDriver is null. Cannot take screenshot for test: " + testName);
            return new byte[0];
        }
    }

    @Attachment(value="Text attachment of {0}", type="text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        suiteStartTime = System.currentTimeMillis();
        testCount = 0;
        passCount = 0;
        failCount = 0;
        
        String suiteName = iTestContext.getSuite().getName();
        logger.info("[SUITE START] ========================================");
        logger.info("[SUITE] Suite Name: " + suiteName);
        logger.info("[SUITE] Total Tests: " + iTestContext.getAllTestMethods().length);
        logger.info("[SUITE] ========================================");
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        testCount++;
        String testClass = getTestClassName(iTestResult);
        String testMethod = getTestMethodName(iTestResult);
        
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║ [TEST #" + testCount + "] Starting: " + testClass + "." + testMethod);
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        logger.info("[TEST #" + testCount + " START] " + testClass + "." + testMethod);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        passCount++;
        String testClass = getTestClassName(iTestResult);
        String testMethod = getTestMethodName(iTestResult);
        long duration = iTestResult.getEndMillis() - iTestResult.getStartMillis();
        
        System.out.println("║ ✅ PASSED: " + testClass + "." + testMethod + " (" + duration + "ms)");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        logger.info("[TEST #" + testCount + " PASSED] " + testClass + "." + testMethod + " (" + duration + "ms)");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        failCount++;
        String testClass = getTestClassName(iTestResult);
        String testMethod = getTestMethodName(iTestResult);
        long duration = iTestResult.getEndMillis() - iTestResult.getStartMillis();
        
        System.out.println("║ ❌ FAILED: " + testClass + "." + testMethod + " (" + duration + "ms)");
        System.out.println("║ Error: " + iTestResult.getThrowable().getMessage());
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        logger.error("[TEST #" + testCount + " FAILED] " + testClass + "." + testMethod);
        logger.error("Error: " + iTestResult.getThrowable().getMessage());
        
        Object testClass2 = iTestResult.getInstance();
        WebDriver webDriver = ((BaseTest) testClass2).getDriver();

        if (webDriver instanceof WebDriver) {
            System.out.println("📸 Screenshot captured for: " + testMethod);
            saveScreenshotPNG(testMethod, webDriver);
        }
        saveTextLog(testMethod + " failed and screenshot taken!");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        String testClass = getTestClassName(iTestResult);
        String testMethod = getTestMethodName(iTestResult);
        
        System.out.println("║ ⏭️  SKIPPED: " + testClass + "." + testMethod);
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        logger.info("[TEST #" + testCount + " SKIPPED] " + testClass + "." + testMethod);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        long suiteDuration = System.currentTimeMillis() - suiteStartTime;
        
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║ [SUITE FINISHED] Results Summary");
        System.out.println("║ Total Tests: " + testCount);
        System.out.println("║ ✅ Passed: " + passCount);
        System.out.println("║ ❌ Failed: " + failCount);
        System.out.println("║ ⏭️  Skipped: " + iTestContext.getSkippedTests().size());
        System.out.println("║ Duration: " + (suiteDuration / 1000) + "s");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        logger.info("[SUITE FINISHED] Total: " + testCount + ", Passed: " + passCount + ", Failed: " + failCount + ", Duration: " + (suiteDuration / 1000) + "s");
    }
}
