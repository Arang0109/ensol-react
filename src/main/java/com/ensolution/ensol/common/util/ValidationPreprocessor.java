package com.ensolution.ensol.common.util;

public class ValidationPreprocessor {
  static public String normalizeInput(String input) {
    return input == null ? null : input.replaceAll("\\s+", "");
  }
  
  static public String toLowerTrimmed(String input) {
    return input == null ? null : input.trim().toLowerCase();
  }
}