package factoryBrowser;

import utils.DeviceConfig;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class MobileSafariDriverManager implements BrowserFactory {
    private final DeviceConfig deviceConfig;

    public MobileSafariDriverManager(DeviceConfig deviceConfig) {
        this.deviceConfig = deviceConfig;
    }

    @Override
    public WebDriver getBrowserDriver() {
        IOSDriver driver;
//        DeviceConfig deviceConfig = new DeviceConfig("iOS");
        // Khởi tạo cấu hình cho iOS (tương tự như Android)
        XCUITestOptions options = new XCUITestOptions()
                .setPlatformName(deviceConfig.getPlatformName())
                .setPlatformVersion(deviceConfig.getPlatformVersion())
                .setDeviceName(deviceConfig.getDeviceName()) // hoặc tên thiết bị thật
//                .setBundleId("com.tuhuynh.sdetproecommerce")
//                .setApp(System.getProperty("user.dir")+"/apps/sdetpro-ecommerce.app")
                .setAutomationName(deviceConfig.getAutomationName())
                .setUdid(deviceConfig.getUdid())
                .setWdaLocalPort(deviceConfig.getWdaLocalPort());
        options.setCapability("browserName", deviceConfig.getBrowserName());
        options.setCapability("safari:useSimulator", deviceConfig.isUseSimulator());

        try {
            URL appiumServerUrl = new URL("http://127.0.0.1:" + deviceConfig.getPort() + "/");
            System.out.println(appiumServerUrl.toString());
            driver = new IOSDriver(appiumServerUrl, options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        } catch (MalformedURLException e) {
            throw new RuntimeException("Không thể khởi tạo phiên Appium cho iOS", e);
        }
        return driver;
    }
}
