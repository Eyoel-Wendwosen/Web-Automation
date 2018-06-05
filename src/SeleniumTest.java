import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;

public class SeleniumTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
//        System.setProperty("webdriver.gecko.driver", "geckodriver");


//        FirefoxOptions options = new FirefoxOptions();
//        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        ChromeOptions options = new ChromeOptions();
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        capabilities.setCapability("pageLoadStrategy", PageLoadStrategy.NONE);
//        WebDriver Driver = new ChromeDriver(capabilities);
//        webDriver.navigate().to("http://www.longer_pageload_url.com");


        WebDriver webDriver = new ChromeDriver();

//        GradeCheck gradeCheck = new GradeCheck();

        News news = new News();
        FetchNews newsl = new FetchNews();


        newsl.fetchNews(webDriver);

//        gradeCheck.check(webDriver);

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        webDriver.close();
//        webDriver.quit();
    }
}

