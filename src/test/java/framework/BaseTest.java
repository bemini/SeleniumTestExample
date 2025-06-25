package framework;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.open;
import static framework.ConstantValues.*;

public class BaseTest {
//    private static  WebDriver driver;

    WebDriver driver;

    public void setUp(WebDriver driver){

        ChromeOptions options = new ChromeOptions();
        WebDriverRunner.setWebDriver(driver); // bind to Selenide

        // Disable Chrome credential service & safety check
        options.addArguments("--disable-features=SafetyCheck");
        options.addArguments("--disable-features=PasswordCheck");

        open(TARGET_URL);

    }

    public void tearDown(WebDriver driver){
        //Logout
        LoginPage.logout();
        driver.close();
    }
}
