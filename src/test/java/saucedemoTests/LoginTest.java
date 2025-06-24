package saucedemoTests;

import com.codeborne.selenide.WebDriverRunner;
import framework.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static framework.ConstantValues.*;

public class LoginTest extends BaseClass {
    WebDriver driver;

    @BeforeMethod
    public void setupTest() {
         driver = new ChromeDriver();
         ChromeOptions options = new ChromeOptions();
         WebDriverRunner.setWebDriver(driver); // bind to Selenide
        
         // Disable Chrome credential service & safety check
         options.addArguments("--disable-features=SafetyCheck");
         options.addArguments("--disable-features=PasswordCheck");

    }

    @Test
    public void loginTest(){

        open("https://www.saucedemo.com/");

        // Check that login page is the initial page.
        $(ID_LOGIN_BUTTON).should(appear);

        //Logim with standard user
        login(STANDARD_USER, PASSWORD);
        $(".title").shouldHave(text("Products"));

        //Logout
        logout();

        // Check that logingout brings  you back to login page
        $(ID_LOGIN_BUTTON).should(appear);

        //Login wit locked out user
        login(LOCKED_OUT_USER, PASSWORD);
        $(ERROR_LOCKEDOUT_USER).shouldHave(text("Epic sadface: Sorry, this user has been locked out."));


    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }

}
