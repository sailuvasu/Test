package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public abstract class GenericMethods {


    public static void waitForElement(WebDriver driver, WebElement e, String desc) throws IOException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.visibilityOf(e));
            Reporting.writeToReport("PASS", desc);
            Reporting.log.info(desc + " Displayed");
        } catch (Exception ex) {
            Reporting.log.info(ex.getMessage());
            Reporting.writeToReport("FAIL", desc + " failed");

        }

    }

    public static void waitToProceed(long time) throws InterruptedException {
        Thread.sleep(time);
    }

    public static void javaScriptClick(WebDriver driver, WebElement e, String desc) throws IOException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", e);
        Reporting.log.info("Clicked on " + desc);
        Reporting.writeToReport("INFO", "clicked on " + desc);
    }

    public static void softAssert(String actual, String expected) throws IOException {
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(actual, expected);
        Reporting.log.info(actual + " is displayed");
        Reporting.writeToReport("PASS", actual + " : verified and displayed");
    }

    public static List<WebElement> listOfElements(String loc, WebDriver driver) {
        List<WebElement> elems = driver.findElements(By.xpath(loc));
        return elems;

    }


}
