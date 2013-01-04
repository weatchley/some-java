package com.goldenop.util.db;

import java.util.*;
import java.math.*;
import java.lang.*;
import java.text.*;


/**
* AbcCrypt is the Java implementation of an in-house developed encryption algorithm (from perl)
*
* @author   Bill Atchley
*/
public class AbcCrypt {

// ceasar cipher
    /**
    * A java implementation of a ceasar cipher
    *
    * @param c     The character to rotate (char)
    * @param key    The amount to rotate (int)
    * @return c     The rotated character (char)
    */
    public static char caesar(char c, int key) {
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String s;

        if (Character.isLetter(c)) {
            s = (Character.isUpperCase(c)) ? upper : lower;

            int i = 0;
            while (i < 26) {
            	  // extra +26 below because key might be negative
                if (c == s.charAt(i)) return s.charAt((i + key + 26)%26);
                   i++;
            }

        } else {
            return c;
        }
        return c;
    }

// do encrypt
    /**
    * Encrypts a string using given paramenters.
    *
    * @param text     The text to encrypt (String)
    * @param key    The encription key (String)
    * @param depth     The rotation seed (int)
    * @return outLine     The encripted text (String)
    */
    public static String crypt(String text, String key, int depth) {
        depth = (depth > 0) ? depth : 5;
        String outLine = "";
        String testKey = key;
        int charVal;
        NumberFormat form = NumberFormat.getInstance();
        form.setMinimumIntegerDigits(3);
        while (testKey.length() < (text.length() + depth)) {
            testKey += key;
        }
        for (int i=0; i<text.length(); i++) {
            char temp = text.charAt(i);
            for (int j=0; j<depth; j++) {
                char keySeg = testKey.charAt(i + j);
                temp = caesar(temp, (i+1));
                charVal = temp ^ keySeg;
                temp = (char) charVal;
            }
            charVal = (int) temp;
            outLine += form.format(charVal);
        }

        return outLine;
    }

// do decrypt
    /**
    * Decrypts a string using given paramenters.
    *
    * @param text     The text to decrypt (String)
    * @param key    The encription key (String)
    * @param depth     The rotation seed (int)
    * @return outLine     The decripted text (String)
    */
    public static String doDecrypt(String text, String key, int depth) {
        depth = (depth > 0) ? depth : 5;
        String outLine = "";
        String testKey = key;
        int charVal;
        String myText = "";
        for (int i=0; i<text.length(); i += 3) {
            String temp = text.substring(i, i+3);
            int tempInt = Integer.parseInt(temp);
            myText += (char) tempInt;
        }
        while (testKey.length() < (myText.length() + depth)) {
            testKey += key;
        }
        for (int i=0; i<myText.length(); i++) {
            char temp = myText.charAt(i);
            for (int j=0; j<depth; j++) {
                char keySeg = testKey.charAt(i + (depth - j -1));
                charVal = temp ^ keySeg;
                temp = (char) charVal;
                temp = caesar(temp, ((26-(i+1))%26));
            }
            outLine += temp;
        }

        return outLine;
    }

// do genkey
    /**
    * Generates an encryption key of a given size.
    *
    * @param size     The size of key to generate (int)
    * @return key     The generated key (String)
    */
    public static String doGenKey(int size) {
        String outLine = "";
        int keyLength = (size > 0) ? size : 32;
        String testVals = "0123456789abcdefghijklmnopqrstuvwxyz";
        int countOfTestVals = 36;
        java.util.Random generator = new java.util.Random(System.currentTimeMillis());
        String key = "";
        for (int pos = 0; (pos < keyLength); pos++) {
            int loc = generator.nextInt(countOfTestVals);
            key = key + testVals.charAt(loc);
        }

        return key;
    }

}
