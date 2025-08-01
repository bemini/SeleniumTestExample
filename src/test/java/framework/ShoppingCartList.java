package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

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

    public static void navigateToShoppingCartList(WebDriver driver){
        driver.findElement(By.className(SHOPPING_CART)).click();
    }

    public static float calculateTotalCartItemsPrice(List<Float> cartItemsList){
        float totalItemPrice = 0.00F;
        for (Float price : cartItemsList){
            totalItemPrice += price;
        }
        return totalItemPrice;
    }

    public static void clickCheckout(WebDriver driver){
        WebElement checkoutButton = driver.findElement(By.xpath("//button[text()='Checkout']"));
        checkoutButton.click();
    }

    public static double calculateTotal(List<Double>cartItemsList, Double taxRate){
        Double totalItemPrice = 0.00;
        for (Double price : cartItemsList){
            totalItemPrice += price;
        }
        return totalItemPrice * (1 + taxRate);
    }

    public static void clickCancleButton(WebDriver driver){
        WebElement cancleButton = driver.findElement(By.xpath("//button[text()='Cancel']"));
        cancleButton.click();
    }

    public static void clickContinueShopping(WebDriver driver){
        WebElement continueShoppingButton = driver.findElement(By.xpath("//button[text()='Continue Shopping']"));
        continueShoppingButton.click();
    }
}
