package service.validation;

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

}
