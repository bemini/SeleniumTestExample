package sauceDemoTests;

import framework.BaseTest;
import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static framework.ConstantValues.*;
import static framework.InventoryPage.addAllItemstoCart;
import static framework.LoginPage.login;
import static framework.LoginPage.logout;
import static framework.ShoppingCartList.*;

public class CheckoutTest extends BaseTest {

    @BeforeMethod
    public void setupTest(){
        super.setUp();
        login(STANDARD_USER, PASSWORD);

        addAllItemstoCart(driver);
        clickShoppingCartButton(driver);
    }

    @Test
    public void cancelCheckoutTest(){
        /*Should go back to items in the cart*/
        clickCheckoutButton(driver);
        clickCancleButton(driver);
        $(PAGE_TITLE).shouldHave(text("Your Cart"));
        $(CHECKOUT_BUTTON).should(appear);
    }

    @Test
    public void goBetweenPagesTest(){
        //Test that from the shopping cart page we can go to the Products page, and the transition is working as expected
        clickContinueShoppingButton(driver);
        $(PAGE_TITLE).shouldHave(text("Products"));

        clickShoppingCartButton(driver);
        $(PAGE_TITLE).shouldHave(text("Your Cart"));

    }

    @Test
    public void invalidClientInformationTest(){
        clickCheckoutButton(driver);
        $(PAGE_TITLE).shouldHave(text("Checkout: Your Information"));
        clickContinueButton(driver);

        $("h3").shouldHave(text("Error: First Name is required"));
        $(FIRST_NAME).setValue("First");
        clickContinueButton(driver);

        $("h3").shouldHave(text("Error: Last Name is required"));
        $(LAST_NAME).setValue("Last");
        clickContinueButton(driver);

        $("h3").shouldHave(text("Error: Postal Code is required"));
        $(POSTAL_CODE).setValue("A1A 1A1");
        clickContinueButton(driver);

        $(PAGE_TITLE).shouldHave(text("Checkout: Overview"));
    }

    @Test
    public void checkoutOverviewTest(){
        List<Float> prices = getCartItemByPrice(driver);
        float itemTotal =  calculateTotalCartItemsPrice(prices);
        String finishedMessage;

        //begin the checkout
        clickCheckoutButton(driver);

        //Fill out the customer details, and continue to the next page
        $(PAGE_TITLE).shouldHave(text("Checkout: Your Information"));
        $(FIRST_NAME).setValue("Test 1");
        $(LAST_NAME).setValue("Test 2");
        $(POSTAL_CODE).setValue("A1A 1A1");

        clickContinueButton(driver);

        //Double check the Overview page
        $(PAGE_TITLE).shouldHave(text("Checkout: Overview"));

        float total = calculateTotal(itemTotal);
        float displayedTotal = displayedTotal(driver);
        //Check the total price off all cart items
        Assert.assertEquals("Expected total price = 140.34", total, displayedTotal , 0.01);

        clickFinishButton(driver);

        finishedMessage = driver.findElement(FINISHED_HEADER).getText();
        Assert.assertEquals("Was unable to finish transaction", "Thank you for your order!", finishedMessage);

        clickHomeButton(driver);

        //Check that transaction is complete, and user returns back to home page
        //To complete the test logout and check that it goes to the login screen.
        $(PAGE_TITLE).shouldHave(text("Products"));
        logout(driver);
        $(ID_USERNAME).isDisplayed();
    }
}

