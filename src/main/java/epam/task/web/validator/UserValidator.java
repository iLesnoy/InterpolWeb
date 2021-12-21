package epam.task.web.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    public static final Logger logger = LogManager.getLogger();
    private final static String EMAIL_REGEX = "\\w+@\\p{Alpha}+\\.\\p{Alpha}{2,}";
    private final static String PASSWORD_REGEX ="[a-zA-Z\\d]{1,15}";

    public static boolean isValidEmail(String email) {
        logger.debug( "email: " + email);
        boolean isValid = true;
        if (!email.isEmpty()) {
            Pattern pattern = Pattern.compile(EMAIL_REGEX);
            Matcher matcher = pattern.matcher(email);
            isValid = matcher.matches();
        } else {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isValidPassword(String password) {
        logger.debug( "password: " + password);
        if(password.isEmpty()) {
            return false;
        }
        return password.matches(PASSWORD_REGEX);
    }
}