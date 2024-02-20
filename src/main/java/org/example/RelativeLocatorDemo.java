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
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
           WebElement element= (WebElement) RelativeLocator.with(By.tagName("input")).above(By.name("password"));
            highlightElementWithBox(element,driver);
           element.sendKeys("TestDemo");

          /*  element.sendKeys("Test Verification");
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            WebElement element1 = shadow.findElementByXPath("//input[@type='checkbox']");
            highlightElementWithBox(element1,driver);
            element1.click();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.quit();

             */
        }
        catch (Exception e){
            log.error(e.getMessage(),e);
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
}
