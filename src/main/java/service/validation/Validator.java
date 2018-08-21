package service.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    /*добавить проверку на телефон и мэйл*/

    public static boolean isFieldsEmpty(String ... values){
        for(String value : values){
            if (value.isEmpty()){
                return true;
            }
        }
        return false;
    }

    public static boolean isFieldsLonger50Symbols(String ... values){
        for(String value : values){
            if (value.length() > 50){
                return true;
            }
        }
        return false;
    }

    public static boolean isPasswordsUnequal(String password1, String password2){
        return !password1.equals(password2);
    }

    public static boolean isPasswordShorter6Symbols(String password){
        return password.length() < 6;
    }

    public static boolean isEmailCorrect(String email){
        String pattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Matcher matcher = Pattern.compile(pattern).matcher(email);
        return matcher.find();
    }

}
