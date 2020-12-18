package com.yg.demoproject;

import android.util.Log;

import java.util.Random;

public class Utils {
    static char[] mC = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o'
            , 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
            , 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O'
            , 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
            , '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '+', '-', '*', '/', '='
            , '`', '~', '!', '@', '#', '$', '%', '^', '&', '(', ')', '_', '{', '}', '|'
            , ':', '"', '<', '>', '?', '[', ']', '\\', ';', '\'', ',', '.', '/'};


    static char[] mChar = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o'
            , 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
            , 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O'
            , 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    /**
     * @return x: start <= x < end
     */
    public static int randomInt(int start, int end) {
        assert start < end;
        Random random = new Random();
        return random.nextInt(end - start) + start;
    }

    public static String randomString() {
        return randomString(randomInt(5, 10));
    }

    public static String randomString(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int c = randomInt(0, mC.length);
            builder.append(mC[c]);
        }
        return builder.toString();
    }

    public static String randomCString() {
        return randomString(randomInt(5, 10));
    }

    public static String randomCString(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int c = randomInt(0, mChar.length);
            builder.append(mChar[c]);
        }
        return builder.toString();
    }

    public static float randomFloat() {
        Random random = new Random();
        return random.nextFloat();
    }
}
