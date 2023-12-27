package org.example;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

// using Base64
public class Encodedecode {
    String orginalString = "Rathnayake";

    public static void main(String[] args) {
        Encodedecode enc = new Encodedecode();
        System.out.println("Encripted String: "+ enc.encodedString);
        System.out.println("Decripted String: "+ enc.decodedString );

    }

    Base64.Encoder encoder = Base64.getEncoder();
    String encodedString = encoder.encodeToString(orginalString.getBytes(StandardCharsets.UTF_8));


    Base64.Decoder decoder = Base64.getDecoder();
    byte [] decodedByte = decoder.decode(encodedString);

    String decodedString = new String(decodedByte);

}
