package framework;

import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Map;

public class BrowserOptions {

    public static ChromeOptions options(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-default-browser-check");
        options.addArguments("--disable-autofill-keyboard-accessory-view[8]");
        options.addArguments("start-maximized");
        options.addArguments("--incognito");

        // Disable password manager completely
        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "profile.default_content_setting_values.notifications", 2
        ));

        return options;
    }
}
