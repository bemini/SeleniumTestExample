package sauceDemoTests;

import framework.BaseTest;
import static framework.LoginPage.login;

import framework.LoginPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static framework.ConstantValues.*;

public class LoginTest extends BaseTest {

    @BeforeClass
    public void setupTest() {
        super.setUp();
    }

    @Test
    public void loginSuccessTest(){

        // Check that login page is the initial page.
        $(ID_LOGIN_BUTTON).should(appear);

        //Logim with standard user
        login(STANDARD_USER, PASSWORD);
        LoginPage.handlePasswordWarningIfPresent(driver);

        $(PAGE_TITLE).shouldHave(text("Products"));

    }

    @Test
    public void loginLockedTest(){

        //Login wit locked out user
        LoginPage.login(LOCKED_OUT_USER, PASSWORD);
        $(ERROR_LOCKEDOUT_USER).shouldHave(text("Epic sadface: Sorry, this user has been locked out."));
    }

    @Test
    public void loginFailedTest(){
        LoginPage.login("Test_user", "password");
        $(ERROR_LOCKEDOUT_USER).shouldHave(text("Epic sadface: Username and password do not match any user in this service"));
    }

}
