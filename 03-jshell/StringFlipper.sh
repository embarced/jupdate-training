#!/usr/bin/java --source 11

public class StringFlipper {
    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("at least one argument needed");
        }
        System.out.println(flip(args[0]));
    }

    public static String flip(String s) {
        StringBuilder flippedString = new StringBuilder();
        for (int i = (s.length() - 1); i >= 0; i--) {
            flippedString.append(s.charAt(i));
        }
        return flippedString.toString();
    }

}