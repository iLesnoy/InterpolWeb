package by.petrorvskiy.webtask.util;

import java.math.BigInteger;
import java.util.Base64;

public class PasswordEncoder {

    private PasswordEncoder() {
    }


    public static String encodePassword(String password) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] bytesEncoded = encoder.encode(password.getBytes());
        BigInteger bigInt = new BigInteger(1, bytesEncoded);
        return bigInt.toString(15);
    }
}
