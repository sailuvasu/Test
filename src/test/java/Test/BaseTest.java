package Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ExcelConnection;
import utils.Reporting;
import utils.Screenshot;
import utils.publicVariables;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static WebDriver driver = null;

    public BaseTest(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;

    }

    public BaseTest() {

    }

    @BeforeSuite
    public void beforeSuiteMethod() {
        Reporting.startReporting();

    }

    @BeforeClass
    public void beforeClassMethod() {

    }

    @BeforeMethod
    public void beforeMethod(Method testMethod) {
        setup("chrome");
        Reporting.logger = Reporting.extent.createTest(testMethod.getName());

    }

    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            Reporting.logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            Reporting.logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));

            String temp = Screenshot.getScreenshot(driver);

            Reporting.logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        } else if (result.getStatus() == ITestResult.SKIP) {
            Reporting.logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            Reporting.logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
        }
        driver.quit();

    }

    @AfterSuite
    public void afterSuiteMethod() {
        Reporting.endReporting();

    }

    @AfterClass
    public void afterClassMethod() {
        //Reporting.endReporting();

    }

    public static void setup(String browser) {
        publicVariables.data = ExcelConnection.getTestData();
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get(publicVariables.url);



        }
    }


}
