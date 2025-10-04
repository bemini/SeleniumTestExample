package saucedemoTests;

import framework.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static framework.ConstantValues.*;
import static framework.InventoryPage.addAllItemstoCart;
import static framework.LoginPage.login;
import static framework.ShoppingCartList.*;
import static framework.BrowserOptions.*;

public class CheckoutTest extends BaseTest {

//    WebDriver driver = new FirefoxDriver();
    WebDriver driver = new ChromeDriver(options());
//    WebDriver driver = new EdgeDriver();
//    WebDriver driver = new SafariDriver();

    @BeforeTest
    public void setupTest(){
        super.setUp(driver);
        login(STANDARD_USER, PASSWORD);

        addAllItemstoCart(driver);
        navigateToShoppingCartList(driver);
    }

    @AfterTest
    public void tearDownTest(){
        super.tearDown(driver);
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void gotoClientInfoPageTest() {
        clickCheckout(driver);
        new WebDriverWait(driver, Duration.ofMillis(200));
        //Check to see we landed on the customer info page
        $(PAGE_TITLE).shouldHave(text("Checkout: Your Information"));
    }

    @Test
    public void cancelCheckoutTest(){
        /*Should go back to items in the cart*/
        clickCheckout(driver);
        clickCancleButton(driver);
        $(PAGE_TITLE).shouldHave(text("Your Cart"));
    }
}
