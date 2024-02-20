package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

@Slf4j
public class RelativeLocatorDemo {
    WebDriver driver;
    @Test
    public void AutomateShadowElement(){
        try {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            Thread.sleep(6000);
           WebElement element = driver.findElement(RelativeLocator.with(By.tagName("input")).above(By.name("password")));
            highlightElementWithBox(element,driver);
           element.sendKeys("Admin");
           WebElement element1= driver.findElement(RelativeLocator.with(By.tagName("input")).below(By.name("username")));
            highlightElementWithBox(element1,driver);
            element1.sendKeys("admin123");
            WebElement element2=driver.findElement(RelativeLocator.with(By.tagName("button")).below(By.name("username")));
            highlightElementWithBox(element2,driver);
            element2.click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.quit();

        }
        catch (Exception e){
            log.error(e.getMessage(),e);
            driver.quit();
        }
    }

    public void highlightElementWithBox(WebElement element, WebDriver driver) {
        try {
//            elementClickableWait(driver,element);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.border='2px solid lightcoral'", element);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    public void pageLoadWait(WebDriver driver) {

        new WebDriverWait(driver, Duration.ofSeconds(15)).until((ExpectedCondition<Boolean>) webDriver ->
                (Boolean) ((JavascriptExecutor) webDriver).executeScript("return (window.jQuery != null) ? jQuery.active === 0 : true"));
        log.debug("xhr ready");
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(webDriver ->
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        log.debug("html ready");
    }
}
