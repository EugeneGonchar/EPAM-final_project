package service.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final String PASSPORT_DATA_PATTERN = "[A-Za-z-\\s]+";
    private static final String EMAIL_PATTERN = "^(?!(?:(?:\\x22?\\x5C[\\x00-\\x7E]\\x22?)|(?:\\x22?[^\\x5C\\x22]\\x22?)){255,})(?!(?:(?:\\x22?\\x5C[\\x00-\\x7E]\\x22?)|(?:\\x22?[^\\x5C\\x22]\\x22?)){65,}@)(?:(?:[\\x21\\x23-\\x27\\x2A\\x2B\\x2D\\x2F-\\x39\\x3D\\x3F\\x5E-\\x7E]+)|(?:\\x22(?:[\\x01-\\x08\\x0B\\x0C\\x0E-\\x1F\\x21\\x23-\\x5B\\x5D-\\x7F]|(?:\\x5C[\\x00-\\x7F]))*\\x22))(?:\\.(?:(?:[\\x21\\x23-\\x27\\x2A\\x2B\\x2D\\x2F-\\x39\\x3D\\x3F\\x5E-\\x7E]+)|(?:\\x22(?:[\\x01-\\x08\\x0B\\x0C\\x0E-\\x1F\\x21\\x23-\\x5B\\x5D-\\x7F]|(?:\\x5C[\\x00-\\x7F]))*\\x22)))*@(?:(?:(?!.*[^.]{64,})(?:(?:(?:xn--)?[a-z0-9]+(?:-[a-z0-9]+)*\\.){1,126}){1,}(?:(?:[a-z][a-z0-9]*)|(?:(?:xn--)[a-z0-9]+))(?:-[a-z0-9]+)*)|(?:\\[(?:(?:IPv6:(?:(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){7})|(?:(?!(?:.*[a-f0-9][:\\]]){7,})(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,5})?::(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,5})?)))|(?:(?:IPv6:(?:(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){5}:)|(?:(?!(?:.*[a-f0-9]:){5,})(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,3})?::(?:[a-f0-9]{1,4}(?::[a-f0-9]{1,4}){0,3}:)?)))?(?:(?:25[0-5])|(?:2[0-4][0-9])|(?:1[0-9]{2})|(?:[1-9]?[0-9]))(?:\\.(?:(?:25[0-5])|(?:2[0-4][0-9])|(?:1[0-9]{2})|(?:[1-9]?[0-9]))){3}))\\]))$";
    private static final String PHONE_PATTERN = "\\+\\d{3}\\(\\d{2}\\)\\d{3}-\\d{2}-\\d{2}";
    private static final String DATE_PATTERN = "20(1[8,9]|2[0-2])-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\\d|3[0-1]) ([0-1]\\d|2[0-3]):([0-5]\\d)";
    /*добавить проверку на телефон и мэйл*/

    public static boolean isFieldsEmpty(String ... values){
        for(String value : values){
            if (value.isEmpty()){
                return true;
            }
        }
        return false;
    }

    public static boolean isFieldsLongerNSymbols(int n, String ... values){
        for(String value : values){
            if (value.length() > n){
                return true;
            }
        }
        return false;
    }

    public static boolean isFieldsShorterNSymbols(int n, String ... values){
        for(String value : values){
            if (value.length() < n){
                return true;
            }
        }
        return false;
    }

    public static boolean isEmailValid(String email){
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public static boolean isPhoneValid(String phone){
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phone);
        return matcher.find();
    }

    public static boolean isPassportDataValid(String ... values){
        Pattern pattern = Pattern.compile(PASSPORT_DATA_PATTERN);
        for(String value : values){
            Matcher matcher = pattern.matcher(value);
            if (!matcher.find()){
                return false;
            }
        }
        return true;
    }

    public static boolean isPasswordsUnequal(String password1, String password2){
        return !password1.equals(password2);
    }

    public static boolean isPasswordShorter6Symbols(String password){
        return password.length() < 6;
    }

    public static boolean isDateValid(String date){
        Pattern pattern = Pattern.compile(DATE_PATTERN);
        Matcher matcher = pattern.matcher(date);
        return matcher.find();
    }

}
