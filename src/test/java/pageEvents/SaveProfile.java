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

public class SaveProfile extends BaseTest {
    public SaveProfile(WebDriver driver) {
        super(driver);
    }

    //profile Info
    @FindBy(xpath = "//li/a[text()='Profile']")
    public static WebElement profile;

    @FindBy(xpath = "//div/h3[text()='Basic']")
    public static WebElement profile_basic;
    @FindBy(xpath = "//div/h3[text()='Basic']")
    public static WebElement profile_username;
    @FindBy(xpath = "//div/h3[text()='Basic']")
    public static WebElement profile_firstname;
    @FindBy(xpath = "//div/h3[text()='Basic']")
    public static WebElement profile_lastname;
    @FindBy(xpath = "(//div/h3[text()='Additional Info'])[1]")
    public static WebElement profile_addInfo1;
    @FindBy(xpath = "//input[@id='gender']")
    public static WebElement profile_gender;
    @FindBy(xpath = "//input[@id='age']")
    public static WebElement profile_age;
    @FindBy(xpath = "//textarea[@id='address']")
    public static WebElement profile_address;
    @FindBy(xpath = "//input[@id='phone']")
    public static WebElement profile_phone;
    @FindBy(xpath = "//select[@id='hobby']")
    public static WebElement profile_hobby;

    @FindBy(xpath = "(//div/h3[text()='Additional Info'])[2]")
    public static WebElement profile_addInfo2;

    @FindBy(xpath = "//div/h4[text()='Change Password']")
    public static WebElement profile_changePassword;
    @FindBy(xpath = "//input[@id='currentPassword']")
    public static WebElement profile_currentPassword;
    @FindBy(xpath = "//input[@id='newPassword']")
    public static WebElement profile_newPassword;
    @FindBy(xpath = "//input[@id='newPasswordConfirmation']")
    public static WebElement profile_newPasswordConfirmation;

    @FindBy(xpath = "//button[text()='Save']")
    public static WebElement profile_save;

    @FindBy(xpath = "//div[contains(text(),'The profile has been saved successful')]")
    public static WebElement profile_saveMessage;


    public void enterAndSaveInfo() throws IOException {
        GenericMethods.waitForElement(driver, profile, "Profile page ");
        GenericMethods.javaScriptClick(driver, profile, "Profile");
        GenericMethods.waitForElement(driver, profile_basic, "Basic details ");
        GenericMethods.waitForElement(driver, profile_addInfo1, "Additional Info1 ");
        profile_gender.sendKeys(ExcelConnection.getData("gender"));
        profile_age.sendKeys(ExcelConnection.getData("age"));
        profile_address.sendKeys(ExcelConnection.getData("address"));
        profile_phone.sendKeys(ExcelConnection.getData("phone"));
        profile_hobby.sendKeys(ExcelConnection.getData("hobby"));
        GenericMethods.waitForElement(driver, profile_addInfo2, "Additional Info2");
        Reporting.addScreenshotToReport("PASS", "Entered User Details Successfully", driver);
        profile_save.click();
        GenericMethods.softAssert(profile_saveMessage.getText(), publicVariables.profileSaveMessage);


    }

}
