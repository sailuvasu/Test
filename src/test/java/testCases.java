import org.testng.annotations.Test;
import pageEvents.*;

import java.io.IOException;

public class testCases extends config {

RegistrationPage objRegistrationPage;
LoginPage objLoginPage;
SaveProfile objSaveProfile;
VoteForCar objVoteForCar;
CarModelNavigation objCarModelNavigation;

    @Test(priority = 1,enabled = true)
    public void Verify_RegistrationPage() throws InterruptedException, IOException {
        objRegistrationPage = new RegistrationPage(config.driver);
        objRegistrationPage.LaunchApplication();
        objRegistrationPage.Register();
        objRegistrationPage.navigateToHomePage();
    }

    @Test( priority = 2,enabled = true)
    public void Verify_Login_to_the_application() throws InterruptedException, IOException {
        objLoginPage = new LoginPage(config.driver);
        objLoginPage.login();
        objLoginPage.logout();

    }

    @Test( priority = 3,enabled = true)
    public void Verify_saving_Profile() throws InterruptedException, IOException {

        objLoginPage = new LoginPage(config.driver);
        objLoginPage.login();
        objSaveProfile = new SaveProfile(config.driver);
        objSaveProfile.enterAndSaveInfo();

    }

    @Test( priority = 4,enabled = true)
    public void Verify_voting_for_car() throws InterruptedException, IOException {
        objLoginPage = new LoginPage(config.driver);
        objLoginPage.login();
        objVoteForCar = new VoteForCar(config.driver);
        objVoteForCar.vote();
        objVoteForCar.verifyEnteredVote();
    }

    @Test( priority = 5,enabled = true)
    public void Verify_carsPage_navigation() throws InterruptedException, IOException {

        objCarModelNavigation = new CarModelNavigation(config.driver);
        objCarModelNavigation.verify_voting_without_Login();
        objRegistrationPage = new RegistrationPage(config.driver);
        objRegistrationPage.navigateToHomePage();
        objCarModelNavigation.NavigationOfCars();

    }
}
