package framework;

public class ConstantValues {
    private ConstantValues(){}

    /*
     LOGIN PAGE
    */
    //Credentials
    public static final String STANDARD_USER = "standard_user";
    public static final String LOCKED_OUT_USER = "locked_out_user";
    public static final String PROBLEM_USER = "problem_user";
//    public static final String PERFORMANCE_GLITCH_USER = "performance_glitch_user";
//    public static final String ERROR_USER = "error_user";
//    public static final String VISUAL_USER = "visual_user";
    public static final String PASSWORD = "secret_sauce";

    //Accessible ID's on Login Page
    public static final String ID_USERNAME = "#user-name";
    public static final String ID_PASSWORD = "#password";
    public static final String ID_LOGIN_BUTTON = "#login-button";
    public static final String ERROR_LOCKEDOUT_USER = "h3";


    //Accessible ID's on sidebar Menu
    public static final String MENU_BUTTON = "#react-burger-menu-btn";
    public static final String LOGOUT = "#logout_sidebar_link";


}
