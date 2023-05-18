package pageEvents;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.GenericMethods;
import utils.Reporting;
import utils.publicVariables;

import java.io.IOException;
import java.util.List;

public class CarModelNavigation {

    WebDriver driver;
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

    public CarModelNavigation(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
   /* public void findRank1_car() throws IOException, InterruptedException {
        int rankCol = 0;
        int votesCol = 0;
        int makeCol = 0;
        int modelCol = 0;
        GenericMethods.javaScriptClick(driver,listOfAllCars);
        List<WebElement> cols = GenericMethods.listOfElements(tableCols, driver);
        for (int i = 0; i < cols.size(); i++) {
            if (cols.get(i).getText().equalsIgnoreCase("Make"))
                makeCol = i;
            else if (cols.get(i).getText().equalsIgnoreCase("Model"))
                modelCol = i;
            else if (cols.get(i).getText().equalsIgnoreCase("Rank"))
                rankCol = i;
            else if (cols.get(i).getText().equalsIgnoreCase("Votes"))
                votesCol = i;
        }
        List<WebElement> rows = GenericMethods.listOfElements(tableRows, driver);
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i).findElement(By.xpath("/td[" + rankCol + "]")).getText().equalsIgnoreCase("1")) {
                String make1 = rows.get(i).findElement(By.xpath("/td[" + makeCol + "]/a")).getText();
                String model1 = rows.get(i).findElement(By.xpath("/td[" + modelCol + "]/a")).getText();
                String votes = rows.get(i).findElement(By.xpath("/td[" + votesCol + "]/a")).getText();

                Reporting.writeToReport("INFO", "Car Make with rank1:" + make1);
                Reporting.writeToReport("INFO", "Car Model with rank1 :" + model1);
                Reporting.writeToReport("INFO", "votes for rank1 :" + votes);
                Reporting.addScreenshotToReport("PASS", "Rank 1 Car is found", driver);
            }

        }
    }*/

    public void NavigationOfCars() throws IOException, InterruptedException {
        int totalCars = 0;

        while (GenericMethods.listOfElements(tableRows, driver).size() != 0) {
            totalCars += GenericMethods.listOfElements(tableRows, driver).size();
            GenericMethods.javaScriptClick(driver, nextPage,"Moved to Next Page");
            GenericMethods.waitToProceed(4000);
        }
        Reporting.writeToReport("INFO", "Total Cars: " + totalCars);
        Reporting.writeToReport("PASS", "Navigate through the cars is success");


    }

    public void verify_voting_without_Login() throws IOException {
        GenericMethods.javaScriptClick(driver, CarImage,"Car Image");
        GenericMethods.waitForElement(driver, votesCount,"Home Page ");
        GenericMethods.waitForElement(driver, loginToVoteMessage,"login to vote Message");
        GenericMethods.softAssert(loginToVoteMessage.getText(), publicVariables.loginToVoteMessage);
        Reporting.addScreenshotToReport("PASS", "We cannot vote without login", driver);

    }

}

