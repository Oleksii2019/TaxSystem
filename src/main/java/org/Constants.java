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
    public static final String LOGIN_COMMAND = "login";
    public static final String LOGOUT_COMMAND = "logout";
    public static final String REGISTRATION_COMMAND = "registration";
    public static final String MAKE_REPORT_COMMAND = "make-report";

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
    public static final String REPORT_LIST = "report";
    public static final String REG_NAME_PARAMETER = "reg_name";
    public static final String CREATE_PAYER_REPORT_PARAMETER = "createBtn";
    public static final String CREATE_COMPLAINT_PARAMETER = "complaintBtn";
    public static final String EDIT_REPORT_PARAMETER = "editBtn";
    public static final String ACCEPT_REPORT_PARAMETER = "accBtn";
    public static final String REPORT_RECLAMATION_PARAMETER = "reclBtn";
    public static final String REPORT_RECLAMATION_TEXT_PARAMETER = "reclText";
    public static final String SELECTED_REPORT_PARAMETER = "selReport";

    // Date and time format pattern for reading from DB
    // as string and transforming to LocalDateTime
    public static final String DATE_TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final Long DEFAULT_OFFICER_FOR_PAYER = 1L;

    public static final String EXCEPTION_NESSAGE = "Error during execute transaction";



}
