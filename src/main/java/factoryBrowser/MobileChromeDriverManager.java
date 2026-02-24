package factoryBrowser;

import utils.DeviceConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebDriver;

import java.net.URL;
import java.time.Duration;

public class MobileChromeDriverManager implements BrowserFactory {
    private final DeviceConfig deviceConfig;

    public MobileChromeDriverManager(DeviceConfig deviceConfig) {
        this.deviceConfig = deviceConfig;
    }
    @Override
    public WebDriver getBrowserDriver() {
        // Implement mobile Chrome driver setup here
        AndroidDriver driver;
//        DeviceConfig deviceConfig = new DeviceConfig("Android");


        // Khởi tạo cấu hình với UiAutomator2Options
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName(deviceConfig.getPlatformName())
                .setDeviceName(deviceConfig.getDeviceName()) // hoặc tên thiết bị thật
//                .setAppPackage("com.tuhuynh.sdetproecommerce")
//                .setAppActivity("host.exp.exponent.MainActivity")
//                .setApp(System.getProperty("user.dir")+"/apps/sdetpro-ecommerce-android.apk")
                .setAutomationName(deviceConfig.getAutomationName());


        options.setCapability("browserName", deviceConfig.getBrowserName());

        try {
            URL appiumServerUrl = new URL("http://127.0.0.1:4723/");
            driver = new AndroidDriver(appiumServerUrl, options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Không thể khởi tạo phiên Appium");
        }
        return driver;

    }
}
