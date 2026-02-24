package others;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Challenge1 {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.worldometers.info/world-population/");
        Thread.sleep(3000);
        List<WebElement> popList = driver.findElements(By.xpath("//h1/following-sibling::div/span[@class='rts-counter']/span"));

        for(WebElement pop : popList){
            System.out.print(pop.getText());

        }

        driver.quit();
    }
}
