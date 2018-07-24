package service.validation;

public class Validator {

    /*добавить проверку на телефон и мэйл*/

    public static boolean isFieldsEmpty(String ... values){
        boolean result = false;
        for(String value : values){
            result = result || value.isEmpty();
        }
        return result;
    }

    public static boolean isPasswordsUnequal(String password1, String password2){
        return !password1.equals(password2);
    }

    public static boolean isPasswordShorter6Symbols(String password){
        return password.length() < 6;
    }

}
