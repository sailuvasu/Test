package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;


public class Reporting {
    public static ExtentTest logger;
    public static ExtentReports extent;
    public static ExtentHtmlReporter htmlReporter;
    public static String reportPath = System.getProperty("user.dir") + File.separator + "reports" + File.separator + "AutomationReport.html";

    static Logger log = Logger.getLogger(Reporting.class.getName());

    public static void startReporting() {

        htmlReporter = new ExtentHtmlReporter(reportPath);
        htmlReporter.config().setEncoding("uft-8");
        htmlReporter.config().setReportName("Automation Test Results");
        htmlReporter.config().setDocumentTitle("Buggy Cars Rating");
        htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Automation Tester", "Sailaja Vasupalli");
    }

    public static void addScreenshotToReport(String status, String desc, WebDriver driver) throws IOException {
        switch (status.toUpperCase()) {
            case "PASS":
                String temp = Screenshot.getScreenshot(driver);
                logger.pass(desc + ": Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
                break;


            case "FAIL":
                String temp1 = Screenshot.getScreenshot(driver);

                Reporting.logger.fail(desc, MediaEntityBuilder.createScreenCaptureFromPath(temp1).build());
                break;

        }

    }

    public static void writeToReport(String status, String desc) throws IOException {
        switch (status.toUpperCase()) {
            case "PASS":
                logger.log(Status.PASS, desc);
                break;
            case "FAIL":
                logger.log(Status.FAIL, desc);
                break;
            case "INFO":
                logger.log(Status.INFO, desc);
                break;
        }
    }

    public static void endReporting() {
        extent.flush();
        extent = null;
        htmlReporter = null;
    }


}
