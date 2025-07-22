package org.example;

public class ValidParentheses {
  public static boolean isValidParentheses(String string) {
    int balance = 0;
    for (char c : string.toCharArray()) {
      if (c == '(') {
        balance++;
      } else if (c == ')') {
        balance--;
      }
      if (balance < 0) {
        return false;
      }
    }
    return balance == 0;
  }
}
