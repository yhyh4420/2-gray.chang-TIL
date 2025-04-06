package org.til.base64;

import java.util.Base64;

public class BaseMain {

    public static void main(String[] args) {
        String username = "gray";
        String password = "1q2w3e4r!";
        String str = username + ":" + password;
        System.out.println("str = " + str);
        String encodeing = getEncodeing(str);
        String decodeing = getDecodeing(encodeing);
    }

    private static String getEncodeing(String str) {
        String encodeing = Base64.getEncoder().encodeToString(str.getBytes());
        System.out.println("encodeing = " + encodeing);
        return encodeing;
    }

    private static String getDecodeing(String str) {
        String decoding = new String(Base64.getDecoder().decode(str.getBytes()));
        System.out.println("decoding = " + decoding);
        return decoding;
    }
}
