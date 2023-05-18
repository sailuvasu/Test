package utils;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class publicVariables {
    //public static WebDriver dr;
    public static String url = "https://buggy.justtestit.org";
    public static String registerPageHeader="Register with Buggy Cars Rating";

    public static HashMap<String,String> data = new HashMap<>();
    public static String title="Buggy Cars Rating";
    public static String registerSuccessfulMessage="Registration is successful";
    public static String username;
    public static String profileSaveMessage= "The profile has been saved successful";
    public static String voteConfirmationText= "Thank you for your vote!";

    public static String loginToVoteMessage="You need to be logged in to vote.";



}
