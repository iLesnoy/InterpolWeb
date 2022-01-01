package util;

import java.math.BigInteger;
import java.util.Base64;

public class Encoder {
    private Encoder() {
    }


    public static String encodePassword(String password) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] bytesEncoded = encoder.encode(password.getBytes());
        BigInteger bigInt = new BigInteger(1, bytesEncoded);
        String resultHex = bigInt.toString(16);
        return resultHex;
    }
}
