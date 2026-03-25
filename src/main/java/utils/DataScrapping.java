package utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DataScrapping {
    public void scrapData() {
        // Code to scrape data from a website
        WebDriver driver = new ChromeDriver();
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=common/home");
        String pageTitle = driver.getTitle();
        System.out.println("Page Title: " + pageTitle);

        driver.quit();

    }

    public static void main(String[] args) {
        DataScrapping scraper = new DataScrapping();
        scraper.scrapData();
    }

}
