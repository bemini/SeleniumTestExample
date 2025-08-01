package saucedemoTests;

import framework.BaseTest;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static framework.ConstantValues.*;
import static framework.InventoryPage.*;
import static framework.LoginPage.login;

public class ProductListTest extends BaseTest {

    WebDriver driver = new ChromeDriver();

    @BeforeClass
    public void setupTest() {
        super.setUp(driver);
        login(STANDARD_USER,PASSWORD );
    }

    @AfterTest
    public void tearDownTest(){
        super.tearDown(driver);
        if (driver != null) {
            driver.quit();
        }
    }

    //Check that the entire products list is captured
    @Test
    public void productListTest(){
        //Check that we landed on the products page
        $(PAGE_TITLE).shouldHave(text("Products"));

        //The invetory list, key, value pair.
        Map<String,String> productslistMap = getProductNamesAndPrices(driver, INVENTORY_ITEMS);

        //Assert that all 6 products are pulled into the list
        Assertions.assertEquals(6, productslistMap.size());
    }

    //Test is list sorted A-Z
    @Test
    public void aZsortedTest() throws InterruptedException {

        productSort(driver, "az");

        Map<String,String> productslistMap = getProductNamesAndPrices(driver, INVENTORY_ITEMS);
        List<String> actualList = new ArrayList<>(productslistMap.keySet());
        List<String> sortedList = new ArrayList<>(actualList);

        //Sort the list
        sortedList.sort(String.CASE_INSENSITIVE_ORDER);

        //Assert that actual list is sorted A-Z
        Assert.assertEquals("Products are not sorted A-Z", sortedList, actualList);

    }

    //Test is list sorted Z-A
    @Test
    public void zAsortedTest() throws InterruptedException {

        //Sort the product list.
        productSort(driver, "za");

        Map<String,String> productslistMap = getProductNamesAndPrices(driver, INVENTORY_ITEMS);
        List<String> actualList = new ArrayList<>(productslistMap.keySet());
        List<String> sortedList = new ArrayList<>(actualList);

        //Sort the list
        sortedList.sort(String.CASE_INSENSITIVE_ORDER);
        Collections.reverse(sortedList);

        //Assert that actual list is sorted Z-A
        Assert.assertEquals("Products are not sorted Z-A", sortedList, actualList);

    }

    //Test is list sorted (low to high)
    @Test
    public void lowToHighSortedTest() throws InterruptedException {

        //Sort the product list
        productSort(driver, "lohi");

        Map<String,String> productslistMap = getProductNamesAndPrices(driver, INVENTORY_ITEMS);
        List<String> actualList = new ArrayList<>(productslistMap.values());
        List<String> sortedList = new ArrayList<>(actualList);

        Assert.assertEquals("Price is not sorted Low to High", sortedList, actualList);

    }

    //Test is list sorted (high to low)
    @Test
    public void highToLowSortedTest() throws InterruptedException {

        //Sort the product list
        productSort(driver, "hilo");

        Map<String,String> productslistMap = getProductNamesAndPrices(driver, INVENTORY_ITEMS);
        List<String> actualList = new ArrayList<>(productslistMap.values());
        List<String> sortedList = new ArrayList<>(actualList);

        Assert.assertEquals("Price is not sorted Low to High", sortedList, actualList);

    }

    @Test
    public void sortAndAddToCartTest() throws InterruptedException {

        // Sort A-Z
        productSort(driver, "az");
        addItemToCart(driver);

        // Sort Z-A
        productSort(driver, "za");
        addItemToCart(driver);

        // Sort Low to High
        productSort(driver, "lohi");
        addItemToCart(driver);

        // Sort High to Low
        productSort(driver, "hilo");
        addItemToCart(driver);

        new WebDriverWait(driver, Duration.ofSeconds(5));
        // Assert cart has 4 items
        int badgeCount = getBadgeCount(driver);
        Assert.assertEquals("Expected badge count = 4",4, badgeCount);

        removeItemFromCart(driver);
        new WebDriverWait(driver, Duration.ofSeconds(5));

        // Assert cart has 3 items
        int badgeCount2 = getBadgeCount(driver);
        Assert.assertEquals("Expected badge count = 3",3, badgeCount2);
        new WebDriverWait(driver, Duration.ofMillis(3000));
    }
}
