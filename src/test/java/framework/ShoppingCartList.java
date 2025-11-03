package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static framework.InventoryPage.*;
import static framework.ConstantValues.*;

public class ShoppingCartList {


    public static List<String> getCartItemByName(WebDriver driver) {
        Map<String, String> productsListMap = getProductNamesAndPrices(driver, SHOPPING_CART_ITEMS);
        return new ArrayList<>(productsListMap.keySet());
    }

    public static List<Float> getCartItemByPrice(WebDriver driver){
        Map<String, String> productsListMap = getProductNamesAndPrices(driver, SHOPPING_CART_ITEMS);
        List<String> stringPrice = new ArrayList<>(productsListMap.values());

        //Convert the price string to a double
        List<Float> prices = new ArrayList<Float>(pricesList(stringPrice));
        return prices;
    }

    public static void clickShoppingCartButton(WebDriver driver){
        driver.findElement(By.className(SHOPPING_CART)).click();
    }

    public static float calculateTotalCartItemsPrice(List<Float> cartItemsList){
        float totalItemPrice = 0.00F;
        for (Float price : cartItemsList){
            totalItemPrice += price;
        }
        return totalItemPrice;
    }

    public static void clickCheckoutButton(WebDriver driver){
        WebElement checkoutButton = driver.findElement(CHECKOUT_BUTTON);
        checkoutButton.click();
    }

    public static float calculateTotal(float itemTotal){
        float tax = itemTotal * 0.08F;
        return itemTotal + tax;
    }

    public static float displayedTotal (WebDriver driver){
        WebElement totalElement = driver.findElement(SUMMARY_TOTAL);
        String totalText = totalElement.getText().trim().replaceAll("\\s+", " ");

        // --- Extract numeric part ---
        Pattern pattern = Pattern.compile("\\d+\\.\\d+");
        Matcher matcher = pattern.matcher(totalText);

        float displayedTotal = 0.0F;
        if (matcher.find()) {
            displayedTotal = Float.parseFloat(matcher.group());
        } else {
            throw new AssertionError("No numeric total found in text: " + totalText);
        }

        return  displayedTotal;
    }

    public static void clickCancleButton(WebDriver driver){
        WebElement cancleButton = driver.findElement(CANCEL_SHOPPING);
        cancleButton.click();
    }

    public static void clickContinueShoppingButton(WebDriver driver){
        WebElement continueShoppingButton = driver.findElement(CONTINUE_SHOPPING_BUTTON);
        continueShoppingButton.click();
    }

    public static void clickContinueButton(WebDriver driver){
        WebElement continueButton = driver.findElement(CONTINUE_BUTTON);
        continueButton.click();
    }

    public static void clickFinishButton(WebDriver driver){
        WebElement continueButton = driver.findElement(FINISH_BUTTON);
        continueButton.click();
    }

    public static void clickHomeButton(WebDriver driver){
        WebElement continueButton = driver.findElement(BACK_HOME);
        continueButton.click();
    }
}
