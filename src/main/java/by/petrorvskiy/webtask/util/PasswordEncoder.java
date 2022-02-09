package by.petrorvskiy.webtask.util;

import java.math.BigInteger;
import java.util.Base64;


/**
 * {@code PasswordEncoder} util class to help encode password
 */
public class PasswordEncoder {

    private PasswordEncoder() {
    }

    /**
     * {@code encode} method to encode password as byte array to string
     * @param password - password as byte array
     * @return encoded password
     */
    public static String encodePassword(String password) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] bytesEncoded = encoder.encode(password.getBytes());
        BigInteger bigInt = new BigInteger(1, bytesEncoded);
        return bigInt.toString(15);
    }
}
