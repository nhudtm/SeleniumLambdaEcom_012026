package reportConfig;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import commons.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

public class AllureTestListener extends BaseTest implements ITestListener {
    private static final Logger logger = LogManager.getLogger(AllureTestListener.class);
    private static final long SLOW_TEST_THRESHOLD_MS = 120_000;
    private static final AtomicInteger testCount = new AtomicInteger(0);
    private static final AtomicInteger passCount = new AtomicInteger(0);
    private static final AtomicInteger failCount = new AtomicInteger(0);
    private static final AtomicInteger skipCount = new AtomicInteger(0);
    private static final ConcurrentLinkedQueue<Long> testDurations = new ConcurrentLinkedQueue<>();
    private static final AtomicBoolean suiteMetricsInitialized = new AtomicBoolean(false);
    private static final Map<String, Long> contextStartTimes = new ConcurrentHashMap<>();
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
        long contextStartTime = System.currentTimeMillis();
        contextStartTimes.put(iTestContext.getName(), contextStartTime);

        if (suiteMetricsInitialized.compareAndSet(false, true)) {
            suiteStartTime = contextStartTime;
            testCount.set(0);
            passCount.set(0);
            failCount.set(0);
            skipCount.set(0);
            testDurations.clear();
        }
        
        String suiteName = iTestContext.getSuite().getName();
        String contextName = iTestContext.getName();
        long threadId = Thread.currentThread().getId();

        logger.info("[CONTEXT START] ========================================");
        logger.info("[CONTEXT] Suite: {}, Test Context: {}, Thread: {}", suiteName, contextName, threadId);
        logger.info("[CONTEXT] Planned Methods: {}", iTestContext.getAllTestMethods().length);
        logger.info("[CONTEXT START] ========================================");

        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║ [CONTEXT START] " + contextName + " | Thread: " + threadId);
        System.out.println("║ Suite: " + suiteName);
        System.out.println("║ Planned Methods: " + iTestContext.getAllTestMethods().length);
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        int currentTestCount = testCount.incrementAndGet();
        String testClass = getTestClassName(iTestResult);
        String testMethod = getTestMethodName(iTestResult);
        iTestResult.setAttribute("TEST_START_TIME", System.currentTimeMillis());
        attachEnvironmentDetails(iTestResult);
        
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║ [TEST #" + currentTestCount + "] Starting: " + testClass + "." + testMethod);
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        logger.info("[TEST #" + currentTestCount + " START] " + testClass + "." + testMethod);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        int currentPassCount = passCount.incrementAndGet();
        String testClass = getTestClassName(iTestResult);
        String testMethod = getTestMethodName(iTestResult);
        long duration = recordPerformanceMetric(iTestResult);
        
        System.out.println("║ ✅ PASSED: " + testClass + "." + testMethod + " (" + duration + "ms)");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        logger.info("[PASS #" + currentPassCount + "] " + testClass + "." + testMethod + " (" + duration + "ms)");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        int currentFailCount = failCount.incrementAndGet();
        String testClass = getTestClassName(iTestResult);
        String testMethod = getTestMethodName(iTestResult);
        long duration = recordPerformanceMetric(iTestResult);
        String errorMessage = iTestResult.getThrowable() != null ? iTestResult.getThrowable().getMessage() : "No throwable message";
        
        System.out.println("║ ❌ FAILED: " + testClass + "." + testMethod + " (" + duration + "ms)");
        System.out.println("║ Error: " + errorMessage);
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        logger.error("[FAIL #" + currentFailCount + "] " + testClass + "." + testMethod + " (" + duration + "ms)");
        logger.error("Error: " + errorMessage);
        
        Object testClass2 = iTestResult.getInstance();
        WebDriver webDriver = resolveDriver(iTestResult, testClass2);

        boolean screenshotCaptured = false;
        if (webDriver != null) {
            try {
                System.out.println("📸 Screenshot captured for: " + testMethod);
                saveScreenshotPNG(testMethod, webDriver);
                screenshotCaptured = true;
            } catch (Exception e) {
                logger.warn("Could not capture screenshot for {}.{}: {}", testClass, testMethod, e.getMessage());
            }
        } else {
            logger.warn("WebDriver is null in onTestFailure for {}.{}", testClass, testMethod);
        }

        if (screenshotCaptured) {
            saveTextLog(testMethod + " failed and screenshot taken!");
        } else {
            saveTextLog(testMethod + " failed but screenshot was not available (driver null or capture error).");
        }
    }

    private WebDriver resolveDriver(ITestResult iTestResult, Object testInstance) {
        if (!(testInstance instanceof BaseTest)) {
            return null;
        }

        BaseTest baseTest = (BaseTest) testInstance;

        WebDriver driver = baseTest.getDriver();
        if (driver != null) {
            return driver;
        }

        ITestContext context = iTestResult.getTestContext();
        return baseTest.getDriver(context);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        skipCount.incrementAndGet();
        String testClass = getTestClassName(iTestResult);
        String testMethod = getTestMethodName(iTestResult);
        long duration = recordPerformanceMetric(iTestResult);
        
        System.out.println("║ ⏭️  SKIPPED: " + testClass + "." + testMethod + " (" + duration + "ms)");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        logger.info("[SKIPPED] " + testClass + "." + testMethod + " (" + duration + "ms)");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        long now = System.currentTimeMillis();
        long contextStartTime = contextStartTimes.getOrDefault(iTestContext.getName(), now);
        long contextDuration = Math.max(0, now - contextStartTime);
        long suiteDuration = Math.max(0, now - suiteStartTime);

        int contextPassed = iTestContext.getPassedTests().size();
        int contextFailed = iTestContext.getFailedTests().size();
        int contextSkipped = iTestContext.getSkippedTests().size();
        int contextTotal = contextPassed + contextFailed + contextSkipped;

        MetricsSummary summary = calculateMetricsSummary();
        attachPerformanceSummary(summary, suiteDuration);

        String contextName = iTestContext.getName();
        long threadId = Thread.currentThread().getId();
        
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║ [CONTEXT FINISHED] " + contextName + " | Thread: " + threadId);
        System.out.println("║ Total Tests: " + contextTotal);
        System.out.println("║ ✅ Passed: " + contextPassed);
        System.out.println("║ ❌ Failed: " + contextFailed);
        System.out.println("║ ⏭️  Skipped: " + contextSkipped);
        System.out.println("║ Context Duration: " + (contextDuration / 1000) + "s");
        System.out.println("║ Suite Elapsed: " + (suiteDuration / 1000) + "s");
        System.out.println("║ Global Avg Test: " + summary.avgMs + "ms");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        logger.info("[CONTEXT FINISHED] {} | Thread: {} | Total: {}, Passed: {}, Failed: {}, Skipped: {}, Context Duration: {}s, Suite Elapsed: {}s, Global Avg: {}ms",
            contextName, threadId, contextTotal, contextPassed, contextFailed, contextSkipped,
            (contextDuration / 1000), (suiteDuration / 1000), summary.avgMs);

        contextStartTimes.remove(contextName);
    }

    private long recordPerformanceMetric(ITestResult iTestResult) {
        Object startAttr = iTestResult.getAttribute("TEST_START_TIME");
        long startTime = startAttr instanceof Long ? (Long) startAttr : iTestResult.getStartMillis();
        long endTime = iTestResult.getEndMillis() > 0 ? iTestResult.getEndMillis() : System.currentTimeMillis();
        long duration = Math.max(0, endTime - startTime);

        testDurations.add(duration);
        Allure.parameter("execution_time_ms", duration);
        saveTextLog("Execution Time: " + duration + "ms");

        if (duration > SLOW_TEST_THRESHOLD_MS) {
            logger.warn("[SLOW TEST] {}.{} took {}ms (threshold: {}ms)",
                    getTestClassName(iTestResult), getTestMethodName(iTestResult), duration, SLOW_TEST_THRESHOLD_MS);
            saveTextLog("SLOW TEST ALERT: " + getTestMethodName(iTestResult) + " took " + duration + "ms");
        }
        return duration;
    }

    private void attachEnvironmentDetails(ITestResult iTestResult) {
        Map<String, String> details = new LinkedHashMap<>();
        ITestContext context = iTestResult.getTestContext();

        details.put("suite", context.getSuite().getName());
        details.put("test", context.getName());
        details.put("java", System.getProperty("java.version"));
        details.put("os", System.getProperty("os.name"));

        if (context.getCurrentXmlTest() != null) {
            Map<String, String> xmlParameters = context.getCurrentXmlTest().getAllParameters();
            putIfPresent(details, "env", xmlParameters.get("env"));
            putIfPresent(details, "browser", xmlParameters.get("browserName"));
            putIfPresent(details, "browserVersion", xmlParameters.get("browserVersion"));
            putIfPresent(details, "osVersion", xmlParameters.get("osVersion"));
            putIfPresent(details, "url", xmlParameters.get("url"));
        }

        WebDriver webDriver = resolveDriver(iTestResult, iTestResult.getInstance());
        if (webDriver instanceof RemoteWebDriver) {
            Capabilities capabilities = ((RemoteWebDriver) webDriver).getCapabilities();
            if (capabilities != null) {
                putIfPresent(details, "browser", capabilities.getBrowserName());
                putIfPresent(details, "browserVersion", capabilities.getBrowserVersion());
                Object platformName = capabilities.getCapability("platformName");
                if (platformName != null) {
                    putIfPresent(details, "platform", String.valueOf(platformName));
                }
            }
        }

        StringBuilder environmentText = new StringBuilder();
        for (Map.Entry<String, String> entry : details.entrySet()) {
            if (entry.getValue() != null && !entry.getValue().isBlank()) {
                Allure.parameter(entry.getKey(), entry.getValue());
                environmentText.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
        }

        if (environmentText.length() > 0) {
            Allure.addAttachment("Test Environment", "text/plain", environmentText.toString());
        }
    }

    private void putIfPresent(Map<String, String> details, String key, String value) {
        if (value != null && !value.isBlank()) {
            details.put(key, value);
        }
    }

    private MetricsSummary calculateMetricsSummary() {
        if (testDurations.isEmpty()) {
            return new MetricsSummary(0, 0, 0);
        }

        long total = 0;
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;

        for (Long duration : testDurations) {
            if (duration == null) {
                continue;
            }
            total += duration;
            min = Math.min(min, duration);
            max = Math.max(max, duration);
        }

        long avg = total / Math.max(1, testDurations.size());
        if (min == Long.MAX_VALUE) {
            min = 0;
        }
        if (max == Long.MIN_VALUE) {
            max = 0;
        }

        return new MetricsSummary(avg, min, max);
    }

    private void attachPerformanceSummary(MetricsSummary summary, long suiteDuration) {
        String performanceSummary = "Suite Duration: " + suiteDuration + "ms\n"
                + "Total Tests: " + testCount.get() + "\n"
                + "Passed: " + passCount.get() + "\n"
                + "Failed: " + failCount.get() + "\n"
                + "Skipped: " + skipCount.get() + "\n"
                + "Avg Test Duration: " + summary.avgMs + "ms\n"
                + "Min Test Duration: " + summary.minMs + "ms\n"
                + "Max Test Duration: " + summary.maxMs + "ms\n"
                + "Slow Threshold: " + SLOW_TEST_THRESHOLD_MS + "ms";

        Allure.addAttachment("Performance Summary", "text/plain", performanceSummary);
    }

    private static class MetricsSummary {
        private final long avgMs;
        private final long minMs;
        private final long maxMs;

        private MetricsSummary(long avgMs, long minMs, long maxMs) {
            this.avgMs = avgMs;
            this.minMs = minMs;
            this.maxMs = maxMs;
        }
    }
}
