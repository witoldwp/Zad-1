package zad1;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import zad1.pages.*;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;


public class AddAddress {
    private WebDriver driver;
    private HomePage homePage;
    private LogInToYourAccountPage logInToYourAccount;
    private YourAccountPage yourAccountPage;
    private NewAddressPage newAddressPage;
    private YourAddressPage yourAddressPage;

    @Given("Page (.*) opened in browser")
    public void pageOpenedInBrowser(String url) {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.get(url);
        homePage = new HomePage(driver);
        logInToYourAccount = new LogInToYourAccountPage(driver);
        yourAccountPage = new YourAccountPage(driver);
        newAddressPage = new NewAddressPage(driver);
        yourAddressPage = new YourAddressPage(driver);
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

    @And("click address btn")
    public void clickAddressBtn() {
        yourAccountPage.clickAddFirstAddressBtn();
    }

    @And("address form filled (.*) (.*) (.*) (.*) (.*) (.*)") //  (.*) (.*) (.*) (.*)
    public void fillAddressForm(String alias, String address, String city, int zipCode, int country, int phone) { //, , int zipCode, String country,
        AddressFormData formData = new AddressFormData()
                .setAlias(alias)
                .setAddress(address)
                .setCity(city)
                .setZipCode(zipCode)
                .setCountry(country)
                .setPhoneNumber(phone);
        newAddressPage.funEnterAddressForm(formData);
    }

    @And("click save")
    public void clickSave() {
        newAddressPage.clickSaveBtn();
    }

    @Then("address added")
    public void isAddressAdded() {
        assertTrue(yourAddressPage.isAddressAddSuccess());
    }

    @Then("delete added address")
    public void deleteAddedAddress() {
        yourAddressPage.deleteAddress();
    }

    @And("check if address is deleted")
    public void isAddressDeleted(){
        assertTrue(yourAddressPage.isAddressAddDeleted());
    }

    @And("exit")
    public void closeBrowser() {
        driver.close();
    }
}
