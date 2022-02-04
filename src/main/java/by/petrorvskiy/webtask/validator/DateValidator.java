package by.petrorvskiy.webtask.validator;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidator {


    private static final String DATE_REGEX= "^(19|20)\\d\\d\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";

    public DateValidator() {
    }

    public static boolean applicationDateValidator(LocalDate leadTime){
        boolean isValid ;
        String time = String.valueOf(leadTime);

        if(!time.isEmpty()){
            Pattern pattern = Pattern.compile(DATE_REGEX);
            Matcher matcher = pattern.matcher(time);
            isValid = matcher.matches();
        } else {
            isValid = false;
        }
        return isValid;
    }
}
