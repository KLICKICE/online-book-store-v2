package org.example;

import java.util.regex.*;

public class Main {
    public static void main(String[] args) {
        String text = "He1llo, how a2re y0uo?";
        Pattern pattern = Pattern.compile("[a-zA-Zа-яА-ЯёЁ]+");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}