package service.validation;

import dto.UserDTO;

public class Validator {

    /*добавить проверку на телефон и мэйл*/

    public static boolean isExistEmptyField(UserDTO userDTO){
        return userDTO.getLogin().isEmpty() ||
                userDTO.getFirstName().isEmpty() ||
                userDTO.getLastName().isEmpty() ||
                userDTO.getEmail().isEmpty() ||
                userDTO.getPhone().isEmpty() ||
                userDTO.getPassword().isEmpty() ||
                userDTO.getPassword2().isEmpty();
    }

    public static boolean isLoginOrPasswordFieldsEmpty(UserDTO userDTO){
        return userDTO.getLogin().isEmpty() ||
                userDTO.getPassword().isEmpty();
    }

    public static boolean isPasswordsEqual(UserDTO userDTO){
        return userDTO.getPassword().equals(userDTO.getPassword2());
    }

    public static boolean isPasswordShorter6Symbols(UserDTO userDTO){
        return userDTO.getPassword().length() < 6;
    }

}
