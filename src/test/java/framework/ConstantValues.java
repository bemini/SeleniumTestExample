package framework;

import org.openqa.selenium.By;

public class ConstantValues {
    private ConstantValues(){}

    //Targeted URL
    public static final String TARGET_URL = "https://www.saucedemo.com/";

    //General ID's
    public static final String PAGE_TITLE = ".title";

    /*
     LOGIN PAGE
    */
    //Credentials
    public static final String STANDARD_USER = "standard_user";
    public static final String LOCKED_OUT_USER = "locked_out_user";
    public static final String PASSWORD = "secret_sauce";

    //Accessible ID's on Login Page
    public static final String ID_USERNAME = "#user-name";
    public static final String ID_PASSWORD = "#password";
    public static final String ID_LOGIN_BUTTON = "#login-button";
    public static final String ERROR_LOCKEDOUT_USER = "h3";

    //Main Page ID's
    public static final String SHOPPING_CART = "shopping_cart_link";
    public static final String INVENTORY_ITEMS = "inventory_item";
    public static final String FILTER = "product_sort_container";
    public static final String INVENTORY_ITEM_NAME = "inventory_item_name";
    public static final String INVENTORY_ITEM_PRICE = "inventory_item_price";

    //Shopping Cart Page ID's
    public static final By SHOPPING_CART_BADGE = By.className("shopping_cart_badge");
    public static final String SHOPPING_CART_ITEMS = "cart_item";

    //Accessible ID's on sidebar Menu
    public static final By MENU_BUTTON = By.id("react-burger-menu-btn");
    public static final String LOGOUT =  "//*[text()='Logout']";


}
