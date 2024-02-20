package org.example;

import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;


@Slf4j
public class TestVerification {
    ChromeDriverEx driver;

    @Test
    public void TestDemo() throws Exception {

        log.info("Test execution started");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriverEx();

        log.info("Browser is Launched");
        driver.manage().window().maximize();
        log.info("window is maximized");
        Thread.sleep(3000);

        //driver.get("https://www.bing.com/search?q=google&qs=n&form=QBRE&sp=-1&lq=1&pq=google&sc=1-6&sk=&cvid=F9DE9E5A792C46059959406E3F5FC532&ghsh=0&ghacc=0&ghpl=");
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        log.info("URL is Entered");
        new WebDriverWait(driver, Duration.ofSeconds(20)).until((ExpectedCondition<Boolean>) webDriver ->
                (Boolean) ((JavascriptExecutor) webDriver).executeScript("return (window.jQuery != null) ? jQuery.active === 0 : true"));
        log.debug("xhr ready");
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(webDriver ->
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        log.debug("html ready");
        Thread.sleep(4000);
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        log.info("Username Entered");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
        log.info("Password Entered");
        WebElement element = driver.findElement(By.xpath("//button[contains(@class,'oxd-button oxd-button--medium oxd-button--main orangehrm-login-button')]"));
        element.click();
        log.info("Navigate to Home Page");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Thread.sleep(60000);
        File src = driver.getFullScreenshotAs(OutputType.FILE);;
        String  screenshotName = "WebPage_.jpg";

        File directory = new File("C:\\Users\\YaswanthKumarGollapo\\IdeaProjects\\TestLogProject\\src\\test\\java\\org\\example");
        if (!directory.exists()) {
            directory.mkdir();
        }
        File desc = new File("C:\\Users\\YaswanthKumarGollapo\\IdeaProjects\\TestLogProject\\src\\test\\java\\org\\example" + screenshotName);
        Files.copy(src, desc);
        Thread.sleep(5000);
        driver.quit();

    }
}
