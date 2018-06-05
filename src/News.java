import com.google.gson.JsonObject;
import netscape.javascript.JSObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

public class News {
    private final String ANCHOR_SELECTOR = "a";
    private final String STORY_BODY = "story-body";
    private final String STORY_BODY_XPATH = "//*[@id=\"page\"]/div[1]/div[2]/div/div[1]/div[1]";
    private WebDriver driver = new ChromeDriver();


    public void fetchNews(WebDriver webDriver)  {

        webDriver.get("http://www.bbc.com/news");

        WebDriverWait wait = new WebDriverWait(webDriver, 120);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='orb-nav-links']/ul/li[2]/a")));


        while (!webDriver.findElement(By.id("latest-stories-tab-container")).isDisplayed()){

        }
        WebElement   latestNews = webDriver.findElement(By.id("latest-stories-tab-container"));
        List<WebElement> elements = latestNews.findElements(By.tagName("div"));
        System.out.println(elements.size());

        WebElement links;
        int count = 0;
        for (WebElement element: elements) {
            links = element;
            System.out.println("Count: " + count);
            System.out.println("Elelment: DIV" + links.getText());
            if (links.findElements(By.tagName(ANCHOR_SELECTOR)).size() != 0) {
                CopyJSON(webDriver, links);
            }
            else {
                System.out.println("Element is Absent");
            }
            count++;
        }

    }

    private void CopyJSON(WebDriver webDriver, WebElement links) {
        driver = webDriver;
        System.out.println("Current URL: " + driver.getCurrentUrl());
        links.click();
        System.out.println("Current URL: " + driver.getCurrentUrl());
        links = driver.findElement(By.xpath(STORY_BODY_XPATH));
        System.out.println("Found the body");
        addToJSON(links);
        driver.navigate().to("http://www.bbc.com/news");
        System.out.println("Current URL: " + driver.getCurrentUrl());
    }

    private void addToJSON(WebElement element){
        JsonObject obj = new JsonObject();
        String Header, Body;

        Header = element.findElement(By.className("story-body__h1")).getText();
        Body = element.findElement(By.className("story-body__inner")).getText();

        obj.addProperty("Header", Header);
        obj.addProperty("Body", Body);
        String json = obj.toString();
        writeToFile(json);
    }

    private void writeToFile(String JSONText){
        try (FileWriter file = new FileWriter("articles.json")) {
            file.append(JSONText);
            System.out.println("Successfully Copied JSON Object to File...");
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    private boolean checkForChildElement(WebElement element, String selector) throws NoSuchElementException{
        if(element.findElements(By.tagName(selector)).size() != 0){
//            String link = element.findElement(By.tagName(selector)).getText();
//            String li = element.getText();
            System.out.println("ID: ");
            return true;
        }else{
            System.out.println("Element is Absent");
            return false;
        }


    }
}

/*
   while (!webDriver.findElement(By.xpath("//*[@id='orb-nav-links']/ul/li[2]/a")).isDisplayed()){
            System.out.println("News displayed: " + webDriver.findElement(By.xpath("//*[@id='orb-nav-links']/ul/li[2]/a")).isDisplayed());
        }
        WebElement latestNews = webDriver.findElement(By.xpath("//*[@id='orb-nav-links']/ul/li[2]/a"));
        System.out.println("Main Menu: "+ latestNews.getText());
        latestNews.click();
 */

/*gs-c-section-link gs-c-section-link--truncate nw-c-section-link nw-o-link nw-o-link--no-visited-state
  gs-c-section-link gs-c-section-link--truncate nw-c-section-link nw-o-link nw-o-link--no-visited-state*/