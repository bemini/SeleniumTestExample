package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

import static framework.ConstantValues.*;

public class InventoryPage {

    private static WebDriverWait wait;

    //Retreive inventory list
    public static Map<String, String> getProductNamesAndPrices(WebDriver driver, String elementClass) {
        Map<String, String> productMap = new LinkedHashMap<>();
        List<WebElement> products = driver.findElements(By.className(elementClass));
        for (WebElement product : products) {
            // Get product name within this container
            String name = product.findElement(By.className(INVENTORY_ITEM_NAME)).getText();

            // Get product price within this container
            String price = product.findElement(By.className(INVENTORY_ITEM_PRICE)).getText();

            // Store in the map
            productMap.put(name, price);
        }
        return productMap;
    }

    //Select a filter, from dropdown
    public static void productSort(WebDriver driver, String sort) throws InterruptedException {
        Select sortedDropdown = new Select(driver.findElement(By.className(FILTER)));
        sortedDropdown.selectByValue(sort);
        new WebDriverWait(driver, Duration.ofMillis(3000));

    }

    //Gather the list of prices, for all items
    public static List<Float> pricesList(List<String> priceStrings) {
        // Convert string to double
        List<Float> prices = new ArrayList<>();
        for (String price : priceStrings) {
            prices.add(Float.parseFloat(price.replace("$", "")));
        }

        // Sort the prices from low to high

        return prices;
    }

    //Click add to cart button.
    public static void addItemToCart(WebDriver driver) {
        driver.findElement(By.xpath("(//button[text()='Add to cart'])[1]")).click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(SHOPPING_CART_BADGE));
    }

    //Iterate through the list and add them all to the shoping cart
    public static void addAllItemstoCart(WebDriver driver) {
        List<WebElement> addButts = driver.findElements(By.xpath("//button[text()='Add to cart']"));
        for (WebElement addButton : addButts) {
            addButton.click();
        }
    }

    public static void removeItemFromCart(WebDriver driver) {
        //Test removing 1 item from with in the products page, and re-check the badge count
        By.xpath("(//button[text()='Remove'])[1]").findElement(driver).click();
    }

    //Iterate through the list and remove them all to the shoping cart
    public static void removeAllItemsFromCart(WebDriver driver) {
        List<WebElement> removeButtons = driver.findElements(By.xpath("//button[text()='Remove']"));
        for (WebElement removeButton : removeButtons) {
            removeButton.click();
        }
    }

    //Get badge count
    public static int getBadgeCount(WebDriver driver){
        WebElement badge = driver.findElement(SHOPPING_CART_BADGE);
        if (badge.isDisplayed()){
            return Integer.parseInt(badge.getText());
        }

        return 0;

    }
}
