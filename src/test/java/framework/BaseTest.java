package framework;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.*;

import static com.codeborne.selenide.Selenide.open;
import static framework.ConstantValues.*;

public class BaseTest {

    public void setUp(WebDriver driver){
        WebDriverRunner.setWebDriver(driver); // bind to Selenide
        open(TARGET_URL);
    }

    public void tearDown(WebDriver driver){
        //Logout
        LoginPage.logout(driver);
        driver.manage().deleteAllCookies();
    }
}
