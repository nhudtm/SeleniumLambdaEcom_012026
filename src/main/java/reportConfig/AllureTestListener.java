package reportConfig;

import commons.BaseTest;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureTestListener extends BaseTest implements ITestListener {
    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
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
    public void onTestFailure(ITestResult iTestResult) {
        Object testClass = iTestResult.getInstance();
        WebDriver webDriver = ((BaseTest) testClass).getDriver();

        if (webDriver instanceof WebDriver) {
            System.out.println("Screenshot captured for test case: " + getTestMethodName(iTestResult));
            saveScreenshotPNG(getTestMethodName(iTestResult), webDriver);
        }
        saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!"); //TC_01_Login_With_Email_Has_Space failed and screenshot taken!
    }

//    @Override
//    public void onTestStart(ITestResult iTestResult) {
//
//    }
//
//    @Override
//    public void onStart(ITestContext iTestContext) {
//
//    }
//

}
