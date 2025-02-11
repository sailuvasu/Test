package pageEvents;


import Test.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.GenericMethods;
import utils.Reporting;
import utils.publicVariables;

import java.io.IOException;

public class CarModelNavigation extends BaseTest {

    public CarModelNavigation(WebDriver driver) {
        super(driver);
    }


    //cars navigation
    @FindBy(xpath = "//div/h2[text()='Overall Rating']/following-sibling::a/img")
    public static WebElement listOfAllCars;

    @FindBy(xpath = "//my-pager/div/div/a[2]")
    public static WebElement nextPage;

    public static String tableCols = "//table/thead/tr/th/a";

    public static String tableRows = "//table/tbody/tr";
    @FindBy(xpath = "//a/img[@title='Guilia Quadrifoglio']")
    public static WebElement CarImage;

    @FindBy(xpath = "//div/h4[contains(text(),'Votes')]")
    public static WebElement votesCount;

    @FindBy(xpath = "//div/p[contains(text(),'You need to be logged in to vote.')]")
    public static WebElement loginToVoteMessage;


    public void NavigationOfCars() throws IOException, InterruptedException {
        int totalCars = 0;
        while (GenericMethods.listOfElements(tableRows, driver).size() != 0) {
            totalCars += GenericMethods.listOfElements(tableRows, driver).size();
            GenericMethods.javaScriptClick(driver, nextPage, "Moved to Next Page");
            GenericMethods.waitToProceed(4000);
        }
        Reporting.writeToReport("INFO", "Total Cars: " + totalCars);
        Reporting.writeToReport("PASS", "Navigate through the cars is success");


    }

    public void verify_voting_without_Login() throws IOException {
        GenericMethods.javaScriptClick(driver, CarImage, "Car Image");
        GenericMethods.waitForElement(driver, votesCount, "Home Page ");
        GenericMethods.waitForElement(driver, loginToVoteMessage, "login to vote Message");
        GenericMethods.softAssert(loginToVoteMessage.getText(), publicVariables.loginToVoteMessage);
        Reporting.addScreenshotToReport("PASS", "We cannot vote without login", driver);

    }

}

