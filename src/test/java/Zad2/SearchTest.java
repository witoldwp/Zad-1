package Zad2;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import zad1.pages.*;

import java.util.concurrent.TimeUnit;

public class SearchTest {
    private WebDriver driver;
    private HomePage homePage;
    private LogInToYourAccountPage logInToYourAccount;
    private YourAccountPage yourAccountPage;

    @Given("Page (.*) opened in browser")
    public void pageOpenedInBrowser(String url) {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.get(url);
        homePage = new HomePage(driver);
        logInToYourAccount = new LogInToYourAccountPage(driver);
        yourAccountPage = new YourAccountPage(driver);

    }

    @When("Sign in link clicked on home page")
    public void signIn() {
        homePage.clickSignIn();
    }

    @And("email entered in login form email input")
    public void enterEmailToSignIn() {
        String emailValue = "HardingGamgee@teleworm.us";
        logInToYourAccount.fillEmailToSignIn(emailValue);
    }

    @And("password entered in login form password input")
    public void enterPasswordToSignIn() {
        String passwordValue = "Ho0owahd";
        logInToYourAccount.fillPasswordToSignIn(passwordValue);
    }

    @And("SignIn button clicked")
    public void clickSignInButton() {
        logInToYourAccount.clickSignInBtn();
    }

    @And("find cloths you want")
    public void find_cloths_you_want() {
        String searchFor = "Hummingbird Printed Sweater";
        yourAccountPage.setSearchBar(searchFor);
    }
}
