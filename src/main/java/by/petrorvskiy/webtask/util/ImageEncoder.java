package by.petrorvskiy.webtask.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * {@code ImageEncoder} util class to help encode image
 */
public class ImageEncoder {

    public ImageEncoder() {
    }

    /**
     * {@code byteImage} method to encode image as byte array to string
     * @param byteImage - image as byte array
     * @return encoded image
     */
    public static String encodeBlob(byte[] byteImage){
        byte[] encodeImageBytes = Base64.getEncoder().encode(byteImage);
        return new String(encodeImageBytes, StandardCharsets.UTF_8);
    }
}
