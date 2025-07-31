package saucedemoTests;

import framework.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static framework.InventoryPage.*;
import static framework.LoginPage.*;
import static framework.ConstantValues.*;
import static framework.ShoppingCartList.*;

public class ShoppingCartTest extends BaseTest {
    WebDriver driver = new ChromeDriver();

    @BeforeClass
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
    public void shoppingCartListTest(){

        // Assert cart has 6 items
        String badgeCount = driver.findElement(By.className(SHOPPING_CART_BADGE)).getText();
        Assert.assertEquals("Expected badge count = 6","6", badgeCount);
        new WebDriverWait(driver, Duration.ofMillis(3000));

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

        // Assert cart has 5 items
        String badgeCount = driver.findElement(By.className(SHOPPING_CART_BADGE)).getText();
        Assert.assertEquals("Expected badge count = 5","5", badgeCount);
        new WebDriverWait(driver, Duration.ofMillis(3000));

        List<Float> prices = getCartItemByPrice(driver);
        //Remove all the items from the list
        removeAllItemsFromCart(driver);
        //Check the total price off all cart items
        Assert.assertEquals("Expected total price = 0.00", 0.00, calculateTotalCartItemsPrice(prices), 0.01);
    }


}
