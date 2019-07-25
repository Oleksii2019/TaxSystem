package org;

/**
 *  For project constants
 */
public class Constants {
    private Constants() {}

    // Session context attribute name for current choice of
    // internationalization
    public static final String LANGUAGE_PARAMETER = "language";

    // Default user name "guest"
    public static final String GUEST_USER_NAME = "Guest";

    // logout command
    public static final String LOGOUT_COMMAND = "logout";

    // Internationalization choice values
    public enum Localization {EN, UK};

    // Separating character for URI-string
    public static final String SEPARATOR = "/";

    // User's mistake sign during input of authorization data
    public static final String INPUT_MISTAKE_SIGN = "inputMistake";

    // Empty string
    public static final String EMPTY_STRING = "";

    // Names of parameters
    public static final String LOGIN_PARAMETER = "login";
    public static final String PASSWORD_PARAMETER = "password";
    public static final String LOGGED_USERS_PARAMETER = "loggedUsers";
    public static final String USER_NAME_PARAMETER = "userName";
    public static final String USER_ROLE_PARAMETER = "role";

    // Date and time format pattern
    public static final String DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";




}
