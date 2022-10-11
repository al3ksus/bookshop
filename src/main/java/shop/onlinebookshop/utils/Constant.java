package shop.onlinebookshop.utils;

public class Constant {

    /** Error users messages */
    public final static String ERROR_LOGIN_OR_PASSWORD_MESSAGE = "Wrong login or password!";
    public final static String ERROR_LOGIN_TAKEN_MESSAGE = "User with this login is already registered!";
    public final static String ERROR_PASSWORD_MESSAGE = "Wrong password!";
    public final static String EMPTY_NAME_FIELD_MESSAGE = "Fill in the First name and Last name fields";

    /** Error books messages */
    public final static String ERROR_ID_MESSAGE = "Wrong id!";
    public final static String EMPTY_TITLE_FIELDS_MESSAGE = "Fill in the Title field";
    public final static String UNACCEPTABLE_LOGIN_MESSAGE = "The login may contain letters of the Latin alphabet, numbers and special characters. The length of the login must be no more than 128 characters.";
    public final static String UNACCEPTABLE_PASSWORD_MESSAGE = "The password can contain Latin letters, numbers and special characters. The password length must be at least 8 and no more than 32 characters.";

    /** Books messages */
    public final static String SUCCESS_PUBLISH_MESSAGE = "Book was published!";
    public final static String SUCCESS_PURCHASE_MESSAGE = "Purchase successful!";

    /** Users messages */
    public final  static String SUCCESS_REGISTRATION_MESSAGE = "Registration successful!";

    /** Prices */
    public static final Integer PRICE_FOR_PUBLISH = 10;
    public static final Integer PRICE_FOR_PURCHASE = 15;


}
