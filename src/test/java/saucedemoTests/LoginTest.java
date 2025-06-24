package saucedemoTests;

import framework.BaseClass;
import org.junit.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static framework.ConstantValues.*;

public class LoginTest extends BaseClass {

    @Test
    public void loginTest(){
        open("https://www.saucedemo.com/");

        // Check that login page is the initial page.
        $(ID_LOGIN_BUTTON).should(appear);

        //Logim with standard user
        login(STANDARD_USER, PASSWORD);
        $(".title").shouldHave(text("Products"));

        //Logout
        logout();

        // Check that logingout brings  you back to login page
        $(ID_LOGIN_BUTTON).should(appear);

        //Login wit locked out user
        login(LOCKED_OUT_USER, PASSWORD);
        $(ERROR_LOCKEDOUT_USER).shouldHave(text("Epic sadface: Sorry, this user has been locked out."));


    }
}
