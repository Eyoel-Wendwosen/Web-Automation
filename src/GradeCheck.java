import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GradeCheck {
    public void check(WebDriver webDriver){

        webDriver.get("http://portal.aait.edu.et");
        // Get the user name and password elements
        WebElement uname = webDriver.findElement(By.id("UserName"));
        WebElement password = webDriver.findElement(By.id("Password"));

        // Pass in the values of the elements
        uname.sendKeys("ATR/7467/08");
        password.sendKeys("AAITcollege2017");

        //Get the login button element and click it
        WebElement btn = webDriver.findElement(By.className("btn-success"));
        btn.click();

        WebElement gradeReport = webDriver.findElement(By.cssSelector("#m2"));
        gradeReport.click();

        gradeReport = gradeReport.findElement(By.cssSelector("ul > li:nth-child(1)"));

        gradeReport = gradeReport.findElement(By.tagName("a"));
        gradeReport.click();

        WebElement cgpa = webDriver.findElement(By.cssSelector("body > div.container-fluid.col-md-12 > div > div.col-md-10 > div.col-md-12 > div > div > table > tbody > tr:nth-child(8) > td > p"));
        System.out.println(cgpa.getText());
    }
}