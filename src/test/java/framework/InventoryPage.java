package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

import static framework.ConstantValues.FILTER;

public class InventoryPage {

    private static WebDriverWait wait;

    //Retreive inventory list
    public static Map<String, String> getProductNamesAndPrices(WebDriver driver) {
        Map<String, String> productMap = new LinkedHashMap<>();

        List<WebElement> products = driver.findElements(By.className("inventory_item"));

        for (WebElement product : products) {
            // Get product name within this container
            String name = product.findElement(By.className("inventory_item_name")).getText();

            // Get product price within this container
            String price = product.findElement(By.className("inventory_item_price")).getText();

            // Store in the map
            productMap.put(name, price);
        }

        return productMap;
    }

    //Select a filter, from dropdown
    public static void productSort(WebDriver driver, String sort) throws InterruptedException{

        Select sortedDropdown = new Select(driver.findElement(By.className(FILTER)));

        sortedDropdown.selectByValue(sort);
        new WebDriverWait(driver, Duration.ofMillis(3000));

    }

    //Gather the list of prices, for all items
    public List<Double> priceList(List<String> priceStrings) {
        // Convert string to double
        List<Double> prices = new ArrayList<>();
        for (String price : priceStrings) {
            prices.add(Double.parseDouble(price.replace("$", "")));
        }

        // Sort the prices from low to high
        Collections.sort(prices);

        return prices;
    }

    // Click add to cart button.
    public static void addFirstItemToCart(WebDriver driver) {
        driver.findElement(By.xpath("(//button[text()='Add to cart'])[1]")).click();
    }
}
