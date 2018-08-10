package service.util;

public class PasswordCreator {

    public static String createPassword(){
        double rnd1 = Math.random();
        double rnd2 = Math.pow(rnd1, Math.random());
        String rnd3 = String.valueOf(Math.pow(rnd2, Math.random()));

        return Hash.getCryptoSha256(rnd3).substring(0,8);
    }
}
