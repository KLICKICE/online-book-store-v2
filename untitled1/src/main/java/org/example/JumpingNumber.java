package org.example;

public class JumpingNumber {
  public static String isJumping(int number) {
    if (number < 10) {
      return "Jumping!!";
    }
    char[] charArray = String.valueOf(number).toCharArray();
    for (int i = 0; i < charArray.length - 1; i++) {
      int curr = charArray[i] - '0';
      int next = charArray[i + 1] - '0';
      if (Math.abs(curr - next) != 1) {
        return "Not!!";
      }
      return "Jumping";
    }
    return null;
  }
}
