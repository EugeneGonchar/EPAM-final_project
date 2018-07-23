package service.util;

public class Hash {

    private static String getSha256(String message){
        return org.apache.commons.codec.digest.DigestUtils.sha256Hex(message);
    }

    public static String getCryptoSha256(String message){
        return getSha256(getSha256(message)+"salt");
    }

    public static boolean isHashesEqual(String hash1, String hash2){
        return hash1.equals(hash2);
    }

}
