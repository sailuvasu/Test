package pageEvents;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.apache.log4j.Logger;
import utils.*;

import java.io.IOException;

public class RegistrationPage  {
    WebDriver driver;
    long currentTimeMillis = System.currentTimeMillis();

    @FindBy(xpath = "//a[text()='Register']")
    public static WebElement Register;
    @FindBy(xpath = "//*[text()='Register with Buggy Cars Rating']")
    public static WebElement validateRegister;

    @FindBy(xpath = "//input[@id='username']")
    public static WebElement Register_username;
    @FindBy(xpath = "//input[@id='firstName']")
    public static WebElement Register_firstname;
    @FindBy(xpath = "//input[@id='lastName']")
    public static WebElement Register_lastName;
    @FindBy(xpath = "//input[@id='password']")
    public static WebElement Register_password;
    @FindBy(xpath = "//input[@id='confirmPassword']")
    public static WebElement Register_confirmPassword;
    @FindBy(xpath = "//button[text()='Register']")
    public static WebElement Register_submit;

    @FindBy(xpath = "//div[contains(text(),'Registration is successful')]")
    public static WebElement Register_SuccessfullMessage;
    @FindBy(xpath = "//a[text()='Buggy Rating']")
    public static WebElement homePage;

    @FindBy(xpath = "//a/img[@title='Guilia Quadrifoglio']")
    public static WebElement CarImage;
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    static Logger log = Logger.getLogger(RegistrationPage.class.getName());

    public void LaunchApplication() throws InterruptedException, IOException {

            GenericMethods.waitForElement(driver, CarImage,"Application loaded Successfully");
            GenericMethods.softAssert(driver.getTitle(), publicVariables.title);
            Reporting.writeToReport("PASS", "Application Launched Successfully");

    }

    public  void Register() throws InterruptedException, IOException {


        GenericMethods.waitToProceed(2000);
        GenericMethods.javaScriptClick(driver, Register,"Register");
        GenericMethods.waitForElement(driver, validateRegister,"Navigated to Registration page ");
        GenericMethods.softAssert(validateRegister.getText(), publicVariables.registerPageHeader);
         publicVariables.username = ExcelConnection.getData("login") + currentTimeMillis;
        Register_username.sendKeys(publicVariables.username);
        Register_firstname.sendKeys(ExcelConnection.getData("firstName"));
        Register_lastName.sendKeys(ExcelConnection.getData("lastName"));
        Register_password.sendKeys(ExcelConnection.getData("password"));
        Register_confirmPassword.sendKeys(ExcelConnection.getData("password"));
        Reporting.writeToReport("INFO", "User details entered in Registration page");
        Register_submit.click();
        GenericMethods.waitForElement(driver, Register_SuccessfullMessage,"Registration Success Message");
        GenericMethods.softAssert(Register_SuccessfullMessage.getText(), publicVariables.registerSuccessfulMessage);
        Reporting.addScreenshotToReport("PASS", "Registration successful :"+publicVariables.username+" username is created", driver);
    }
    public void navigateToHomePage() throws IOException {
        homePage.click();
        GenericMethods.waitForElement(driver, CarImage, "Home Page ");
        GenericMethods.softAssert(driver.getTitle(), publicVariables.title);
        Reporting.writeToReport("INFO", "Navigated to Home Page");
    }
}
