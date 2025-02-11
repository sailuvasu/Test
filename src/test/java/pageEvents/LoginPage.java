package pageEvents;

import Test.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ExcelConnection;
import utils.GenericMethods;
import utils.Reporting;
import utils.publicVariables;

import java.io.IOException;

public class LoginPage extends BaseTest {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div/input[@name='login']")
    public static WebElement Login_username;
    @FindBy(xpath = "//div/input[@name='password']")
    public static WebElement Login_Password;
    @FindBy(xpath = "//button[text()='Login']")
    public static WebElement Login_submit;
    @FindBy(xpath = "//li/a[text()='Logout']")
    public static WebElement logout;


    @FindBy(xpath = "//a/img[@title='Guilia Quadrifoglio']")
    public static WebElement CarImage;

    //profile Info
    @FindBy(xpath = "//li/a[text()='Profile']")
    public static WebElement profile;


    public void login() throws IOException {
        Login_username.sendKeys(publicVariables.username);
        Login_Password.sendKeys(ExcelConnection.getData("password"));
        Login_submit.click();
        GenericMethods.waitForElement(driver, CarImage, "Home page ");
        GenericMethods.waitForElement(driver, profile, "profile page ");
        Reporting.addScreenshotToReport("PASS", "Logged in Successfully", driver);
    }

    public void logout() throws IOException {
        GenericMethods.waitForElement(driver, logout, "logout button");
        GenericMethods.javaScriptClick(driver, logout, "logout");
        GenericMethods.waitForElement(driver, Login_submit, "Home Page");
        Reporting.addScreenshotToReport("PASS", "Logged out in Successfully", driver);
    }

}

