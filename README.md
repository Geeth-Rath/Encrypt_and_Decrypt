Java Password Encrypt & Decrypt , Basic + Advanced
Overview
There is often requirement to encrypt sensitive data like passwords secrete keys or any string values there are multiple ways to do it, but here I am explaining simplest and legit way to achieve the same.

In this article, we will learn how to encrypt and decrypt input values like strings, objects, and password-based data using the AES and base 64 algorithm in Java

Securing data transfer is done in multiple ways. But most experts refer to data encryption as the best method and currently, Java AES [Advanced Encryption Standard] is an advanced solution available for ciphering. New algorithms are replacing the old values of DES towards the AES. It has a better legacy of confidential properties, data authentication, and high levels of integrity.

Simple Encryption and decryption Using Java Base64
Java provides a class Base64 to deal with encryption. You can encrypt and decrypt your data by using provided methods. You need to import java.util.Base64 in your source file to use its methods.

Java Base64 Example: Basic Encoding and Decoding

Creating Java project
Go to Eclipse let’s start with creating a standard Java project, by using Java 8 <or any version>.

In eclipse right click in project explorer and say new Java Project

Create new java class
public class JavaBase64 {
     
    public static void main(String[] args) {
        Encoder encoder = Base64.getEncoder();
        String originalString = "YOUR_SECRETE_KEY";
        String encodedString = encoder.encodeToString(originalString.getBytes());
 
        System.out.println("Encrypted Value :: " +encodedString);
        Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(encodedString);
                 
        System.out.println("Decrypted Value :: " +new String(bytes));
    }
 
}
Output

Encrypted Value :: WU9VUl9TRUNSRVRFX0tFWQ==
Decrypted Value :: YOUR_SECRETE_KEY

Advanced Encryption and decryption Using AES
Let’s get into decryption as well as on the encryption with a single key. It is a huge advantage over other methods to secure sensitive information. It is the best solution for government agencies and financial institutions which require protecting sensitive information.

The AES algorithm is an iterative, symmetric-key block cipher that supports cryptographic keys of 128 bits.

Secret Key
There are two ways for generating a secret key in the AES: generating from a random number, or deriving from a given password. For generating a secret key, we can use the KeyGenerator class. Let’s define a method for generating the AES key with the size of n (128, 192, and 256) bits:

Create New Java Class for AES


private static final String ALGORITHM = "AES";
    private static final byte[] keyValue = "1234567891234567".getBytes();
 
private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGORITHM);
        return key;
    }
    
Encryption
To implement input string encryption, we first need to generate the secret key . As the next step, we create an instance from the Cipher class by using the getInstance() method.

What is Cipher ?

The Java Cipher (javax.crypto.Cipher) class represents an encryption algorithm. The term Cipher is standard term for an encryption algorithm in the world of cryptography. That is why the Java class is called Cipher and not e.g. Encrypter / Decrypter or something else.
Commons Codec external lib required

Add below dependancy

<dependency>
    	<groupId>commons-codec</groupId>
    	<artifactId>commons-codec</artifactId>
    	<version>1.15</version>
</dependency>
  	
Additionally, we configure a cipher instance using the init() method with a secret key, IV, and encryption mode. Finally, we encrypt the input string by invoking the doFinal() method. This method gets bytes of input and returns ciphertext in bytes:

public static String encrypt(String valueToEnc, Key key) throws Exception {
 
       
       Cipher cipher = Cipher.getInstance(ALGORITHM);
       cipher.init(Cipher.ENCRYPT_MODE, key);
 
       byte[] encValue = cipher.doFinal(valueToEnc.getBytes());
       byte[] encryptedByteValue = new Base64().encode(encValue);
       System.out.println("Encrypted Value :: " + new String(encryptedByteValue));
 
       return new String(encryptedByteValue);
   }
Decryption

For decrypting an input string, we can initialize our cipher using the DECRYPT_MODE to decrypt the content..


public static String decrypt(String encryptedValue, Key key) throws Exception {
       // Key key = generateKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
         
        byte[] decodedBytes = new Base64().decode(encryptedValue.getBytes());
 
        byte[] enctVal = cipher.doFinal(decodedBytes);
        
        System.out.println("Decrypted Value :: " + new String(enctVal));
        return new String(enctVal);
    }
Main Method

public static void main(String args[]) throws Exception {
         Key key = generateKey();
        String encriptValue = encrypt("YOUR_SECRETE_KEY",key);
        decrypt(encriptValue,key);
 
    }
Output

Encrypted Value :: jpEaC/up3NLMSb7u4wmZs1RV3hoT25hPZn0HoNoosHQ=
Decrypted Value :: YOUR_SECRETE_KEY

Conclusion
In this article, we learned how to encrypt and decrypt input data like strings and password-based data using the Simple Base64 class and advanced AES algorithm in Java.



