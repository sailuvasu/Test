package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    int count = 0;
    int retrycount = 0;

    @Override
    public boolean retry(ITestResult result) {
        while (count < retrycount) {
            count++;
            return true;
        }
        return false;
    }
}
