package org.example;

public class IPDefanger {
  public static String defangIPAddress(String address) {
    String pattern = "[.]";
    String restult = pattern.replaceAll(".", pattern);
  }
}
