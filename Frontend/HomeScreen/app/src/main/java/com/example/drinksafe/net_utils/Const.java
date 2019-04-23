package com.example.drinksafe.net_utils;

/**
 * This class is used to store user info
 *
 * @author Jorden Lee
 * @author Philip Payne
 */
public class Const {
    /**
     * A string for the URL for an image displayed
     */
    public static final String URL_Image = "https://i.pinimg.com/originals/16/cc/a9/16cca90ed9e708f8b19360531463a003.png";

    /**
     * The URL for the server database
     */
    public static final String URL_USER_INFO = "http://cs309-bs-7.misc.iastate.edu:8080/users";

    /**
     * A string which stores the username of the current user.  Updated upon a successful sign in.
     */
    public static String cur_user_name = "";
}
