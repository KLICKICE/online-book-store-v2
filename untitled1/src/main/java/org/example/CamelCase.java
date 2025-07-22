package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CamelCase {
  public static String convertToCamelCase(String string) {
    Pattern pattern = Pattern.compile("[a-zA-Zа-яА-ЯёЁ]+");
    Matcher matcher = pattern.matcher(string);
    StringBuilder stringBuilder = new StringBuilder();
    while (matcher.find()) {
      stringBuilder.append(matcher.group());
    }
    return stringBuilder.toString();
  }
}
