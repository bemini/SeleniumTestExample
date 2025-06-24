package framework;

import static com.codeborne.selenide.Selenide.$;
import static framework.ConstantValues.*;

public class BaseClass {

    //Login
    public void login(String username, String password){
        $(ID_USERNAME).setValue(username);
        $(ID_PASSWORD).setValue(password);
        $(ID_LOGIN_BUTTON).click();
    }

    //Logout
    public void logout(){
        $(MENU_BUTTON).click();
        $(LOGOUT).click();
    }
}
