package org;

/**
 *  For project constants
 */
public class Constants {
    private Constants() {}

    // Session context attribute name for current choice of
    // internationalization
    public static final String LANGUAGE = "language";

    // Internationalization choice values
    public enum Localization { EN, UK };

    // Separating character for URI-string
    public static final String SEPARATOR = "/";

    // User's mistake sign during input of authorization data
    public static final String INPUT_MISTAKE_SIGN = "inputMistake";

    // Empty string
    public static final String EMPTY_STRING = "";

}
