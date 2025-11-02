package sauceDemoTests;

import framework.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static framework.InventoryPage.*;
import static framework.LoginPage.*;
import static framework.ConstantValues.*;
import static framework.ShoppingCartList.*;

public class ShoppingCartTest extends BaseTest {


    @BeforeMethod
    public void setupTest(){
        super.setUp();
        login(STANDARD_USER, PASSWORD);

        addAllItemstoCart(driver);
        navigateToShoppingCartList(driver);
    }

    @Test
    public void shoppingCartListTest(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500));
        // Assert cart has 6 items
        String badgeCount = wait.until(ExpectedConditions.visibilityOfElementLocated(SHOPPING_CART_BADGE)).getText();;
        Assert.assertEquals("Expected badge count = 6","6", badgeCount);

        List<Float> prices = getCartItemByPrice(driver);
        //Assert that all 6 products are pulled into the list
        Assert.assertEquals("Was expecting 6 items",6, prices.size());

        //Check the total price off all cart items
        Assert.assertEquals("Expected total price = 129.94", 129.94, calculateTotalCartItemsPrice(prices), 0.01);
    }

    @Test
    public void removeItemsTest(){

        //Remove an Item, from the list
        removeItemFromCart(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500));

        // Assert cart has 5 items
        String badgeCount =  wait.until(ExpectedConditions.visibilityOfElementLocated(SHOPPING_CART_BADGE)).getText(); //driver.findElement(SHOPPING_CART_BADGE).getText();
        Assert.assertEquals("Expected badge count = 5","5", badgeCount);
        new WebDriverWait(driver, Duration.ofMillis(3000));

        //Remove all the items from the list
        removeAllItemsFromCart(driver);
        List<Float> prices = getCartItemByPrice(driver);
        //Check the total price off all cart items
        Assert.assertEquals("Expected total price = 0.00", 0.00, calculateTotalCartItemsPrice(prices), 0.01);
    }
}
