package sauceDemoTests;

import framework.BaseTest;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static framework.ConstantValues.*;
import static framework.InventoryPage.addAllItemstoCart;
import static framework.LoginPage.login;
import static framework.ShoppingCartList.*;

public class CheckoutTest extends BaseTest {

    @BeforeMethod
    public void setupTest(){
        super.setUp();
        login(STANDARD_USER, PASSWORD);

        addAllItemstoCart(driver);
        navigateToShoppingCartList(driver);
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
