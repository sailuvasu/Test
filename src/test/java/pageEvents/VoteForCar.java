package pageEvents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ExcelConnection;
import utils.GenericMethods;
import utils.Reporting;
import utils.publicVariables;

import java.io.IOException;

public class VoteForCar {
    WebDriver driver;

    //Voting
    @FindBy(xpath = "//textarea[@id='comment']")
    public static WebElement comment;

    @FindBy(xpath = "//button[text()='Vote!']")
    public static WebElement vote;

    @FindBy(xpath = "//div/p[text()='Thank you for your vote!']")
    public static WebElement voteConfirmation;

    @FindBy(xpath = "(//tr/td)[1]")
    public static WebElement table_date;

    @FindBy(xpath = "(//tr/td)[3]")
    public static WebElement table_comment;

    //cars navigation
    @FindBy(xpath = "//div/h2[text()='Overall Rating']/following-sibling::a/img")
    public static WebElement listOfAllCars;

    @FindBy(xpath = "//a[text()='Buggy Rating']")
    public static WebElement homePage;

    @FindBy(xpath = "//a/img[@title='Guilia Quadrifoglio']")
    public static WebElement CarImage;

    //logout
    @FindBy(xpath = "//li/a[text()='Logout']")
    public static WebElement logout;
    public VoteForCar(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void vote() throws IOException {

       GenericMethods.javaScriptClick(driver,CarImage,"Home Page ");
       GenericMethods.waitForElement(driver,vote,"vote button");
       comment.sendKeys(ExcelConnection.getData("comment"));
        GenericMethods.javaScriptClick(driver,vote,"vote");
        GenericMethods.waitForElement(driver,voteConfirmation,"vote confirmation message");
        GenericMethods.softAssert(voteConfirmation.getText(), publicVariables.voteConfirmationText);
        Reporting.addScreenshotToReport("PASS","Voting is Done Successfully",driver);

    }

    public void verifyEnteredVote() throws IOException {

        Reporting.writeToReport("INFO",table_comment.getText()+"- comment entered at "+table_date.getText());
        GenericMethods.softAssert(table_comment.getText(), ExcelConnection.getData("comment"));
        Reporting.addScreenshotToReport("PASS","Entered Voting is verified Successfully",driver);

    }
}
