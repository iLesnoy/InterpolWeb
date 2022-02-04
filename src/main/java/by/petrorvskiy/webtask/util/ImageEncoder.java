package by.petrorvskiy.webtask.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class ImageEncoder {

    public ImageEncoder() {
    }

    public static String encodeBlob(byte[] byteImage){
        byte[] encodeImageBytes = Base64.getEncoder().encode(byteImage);
        return new String(encodeImageBytes, StandardCharsets.UTF_8);
    }
}
