package framework;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static framework.ConstantValues.*;

public class LoginPage {

    //Login
    public static void login(String username, String password){
        if (username == null || username.isEmpty() && password == null || password.isEmpty()){
            $(ID_USERNAME).setValue(STANDARD_USER);
            $(ID_PASSWORD).setValue(PASSWORD);
        }else {
            $(ID_USERNAME).setValue(username);
            $(ID_PASSWORD).setValue(password);
        }
        $(ID_LOGIN_BUTTON).click();
    }

    public static void handlePasswordWarningIfPresent(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // Wait for the OK button to be present if the popup appears
            WebElement okButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//button[text()='OK']")));

            // Check if it's visible and clickable
            if (okButton.isDisplayed() && okButton.isEnabled()) {
                okButton.click();
                System.out.println("Password warning dismissed.");
            }
        } catch (TimeoutException e) {
            // Popup didn't appear — nothing to do
            System.out.println("No password warning popup found.");
        } catch (NoSuchElementException | ElementNotInteractableException e) {
            System.out.println("Popup present but could not interact with OK button.");
        }
    }

    //Logout
    public static void logout(WebDriver driver){
        driver.findElement(MENU_BUTTON).click();
        new WebDriverWait(driver, Duration.ofMillis(100));
        WebElement logoutButton = driver.findElement(By.xpath("//*[text()='Logout']"));
        if (logoutButton.isDisplayed()){
            logoutButton.click();
        }
    }
}
