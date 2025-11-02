package framework;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static framework.BrowserOptions.options;

public class BaseTest{
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        if (driver == null) {
            String browser = System.getProperty("browser", "chrome").toLowerCase();
            switch (browser) {
                case "firefox": driver = new FirefoxDriver(); break;
                case "edge": driver = new EdgeDriver(); break;
                case "safari": driver = new SafariDriver(); break;
                default: driver = new ChromeDriver(options()); break;
            }
            WebDriverRunner.setWebDriver(driver);
            driver.manage().window().maximize();
        }
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
